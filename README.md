# rosieliz-dzhurkova-employees

The purpose of this program is to read from a CSV file of employees' data and find the two employees, which have worked together for the longest period on the same project.

Algorithm: 
The Employee model stores the data upon reading from a given file. The employees are then grouped by the projects they have worked on in a hashmap. Finally, all possible pairs of employees with overlapping working periods on the same project are generated and stored in a sorted set.

Implementation remarks: 
* LocalDate type is used in order to facilitate overlapping working period calculation given a certain start- and end dates.
* Filepicker is implemented using the JFileChooser class.
