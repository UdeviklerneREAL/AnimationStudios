import os
import sys

res = "runGentle.bat "
for arg in range(len(sys.argv)):
    if arg == 0:
        continue
    else:
        res += sys.argv[arg] + " "
os.system(res)
