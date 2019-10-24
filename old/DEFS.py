def maven_jar(group, artifact, version, sha1):
    URL_FORMAT = 'http://central.maven.org/maven2/{0}/{1}/{2}/{1}-{2}.jar'
    remote_file(
      name = 'mvn_{}'.format(artifact),
      out = '{}.jar'.format(artifact),
      url = URL_FORMAT.format(group.replace('.', '/'), artifact, version),
      sha1 = sha1,
    )

    prebuilt_jar(
      name = artifact,
      binary_jar = ':mvn_{}'.format(artifact),
    )
