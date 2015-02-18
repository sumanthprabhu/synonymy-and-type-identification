setwd("/media/Data/IIITB/Semester 2/Competitions/Hackathon/R")
#data_sample <- read.csv("wikipedia_cooccurance_datasets/sample_cooccurrence_graph_with_10007_nodes.csv")
#data <- read.csv("wikipedia_cooccurance_datasets/wiki2006.csv")

input_file <- "wiki2006.csv"
args <- "actor"
file.create("output.csv")
command = paste("java","Hack",input_file, args, ">" , "output.csv")
system(command)
output <- read.csv(file = "output.csv")
colnames(output)<- c("Term", "Score")
new_list <- output[order(output[,1], decreasing = TRUE),]
new_list <- output[,'Term']
write.csv(new_list, 'output.csv')