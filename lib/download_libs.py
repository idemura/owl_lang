from __future__ import absolute_import
from __future__ import print_function

import os.path
import sys
import urllib2
from xml.etree import ElementTree


MVN_REPOS_URL = 'http://central.maven.org/maven2'
MVN_XMLNS = '{http://maven.apache.org/POM/4.0.0}'


def mvn_tag(*name):
    return '/'.join([MVN_XMLNS + p for p in name])


def fetch_file(group, artifact, filename):
    url = '/'.join([
        MVN_REPOS_URL,
        group.replace('.', '/'),
        artifact,
        filename,
    ])
    return urllib2.urlopen(url).read()


def get_version(group, artifact):
    root = ElementTree.fromstring(fetch_file(
        group, artifact, 'maven-metadata.xml'))
    return root.findtext('versioning/release')


def version_name(artifact, version, suffix):
    return version + '/' + artifact + '-' + version + suffix


class Library:
    def __init__(self, artifact, version, deps):
        self.artifact = artifact
        self.version = version
        self.deps = deps

    def format_rule(self, public):
        TAB = ' ' * 4
        # Preserve order of addition (dict shuffles).
        props = [
            ('name', self.artifact),
            ('binary_jar', '{}-{}.jar'.format(self.artifact, self.version)),
        ]
        if public:
            props.append(('visibility', ['PUBLIC']))
        if self.deps:
            props.append(('deps', [':' + d.artifact for d in self.deps]))

        lines = []
        for key, value in props:
            if isinstance(value, list):
                lines += (
                    [TAB + '{} = ['.format(key)] +
                    [TAB * 2 + "'{}',".format(d) for d in value] +
                    [TAB + '],']
                )
            else:
                lines += [TAB + "{} = '{}',".format(key, value)]
        return 'prebuilt_jar(\n' + '\n'.join(lines) + '\n)\n'


def fetch(group, artifact, version):
    version = version or get_version(group, artifact)

    pom_name = version_name(artifact, version, '.pom')
    print('Reading POM', pom_name)
    root = ElementTree.fromstring(fetch_file(group, artifact, pom_name))
    deps = []
    for d in root.findall(mvn_tag('dependencies', 'dependency')):
        if (d.findtext(mvn_tag('optional')) == 'true'
                or d.findtext(mvn_tag('scope')) == 'test'):
            continue;

        deps.append(fetch(
            d.findtext(mvn_tag('groupId')),
            d.findtext(mvn_tag('artifactId')),
            d.findtext(mvn_tag('version'))
        ))

    jar_name = version_name(artifact, version, '.jar')
    print('Reading JAR', jar_name)
    with open(os.path.basename(jar_name), 'w') as f:
        f.write(fetch_file(group, artifact, jar_name))

    return Library(artifact, version, deps)


def walk_lib(lib, public=True):
    s = [lib.format_rule(public)]
    for l in lib.deps:
        s += walk_lib(l, False)
    return s


if __name__ == '__main__':
    if len(sys.argv) != 2:
        print('Usage: download_mvn.py <lib_list>')
        sys.exit(1)

    libs = []
    with open(sys.argv[1], 'rt') as f:
        for s in f:
            s = s.strip()
            if len(s) == 0: continue
            args = s.split(' ')
            if len(args) < 2 or len(args) > 3:
                raise ValueError('invalid libs entry')
            libs.append(fetch(
                group=args[0],
                artifact=args[1],
                version=args[2] if len(args) == 3 else None,
            ))

    with open('BUCK', 'wt') as f:
        rules = []
        for l in libs:
            rules += walk_lib(l)
        f.write('\n'.join(rules))

    sys.exit(0)
