import os

def reformat(java):
    f = open(java, 'r')
    r = ''
    for s in f.readlines():
        r += s.replace('  ', '    ')
    f.close()
    f = open(java, 'w')
    f.write(r)
    f.close()


for root, dirs, files in os.walk('.'):
    dirs[:] = [d for d in dirs if d not in ['buck-out', '.idea', '.buckd']]
    # print files
    # print [os.path.splitext(f) for f in files]
    for java in [os.path.join(root, f)
            for f in files if os.path.splitext(f)[1] == '.java']:
        reformat(java)

