import re

pattern = re.compile(r'^([\d-]{10}),(.+),(.+),(\d+),(\d+),Friendly,.*$')
c_match = 0
c_nomatch = 0

f = open('./results.csv', encoding='utf8')

for line in f:
   res = re.match(pattern, line)
   if res:
      print(f"{res.group(1)} : {res.group(2)} [{res.group(4)} {res.group(5)}] {res.group(3)}\n")
      c_match += 1
   else:
      c_nomatch += 1

print(f"No. de matches: {c_match}\nNo. de no matches: {c_nomatch}")

f.close()