import os, sys

cwd_folder = os.getcwd()
data_folder = os.path.join(cwd_folder,'data')
in_file = os.path.join(data_folder,'short_rand_list.txt')
out_file = os.path.join(data_folder,'rand_list.txt')
out_list = []

with open(in_file,'r') as f:
	for line in f:
		if len(line.strip())>=8 and '-' not in line:
			out_list.append(line.strip())

with open(out_file,'w') as o:
	for line in out_list:
		o.write(line+'\n')
	o.close()

sys.exit()