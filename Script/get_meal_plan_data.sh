#!/bin/bash

echo "Enter your Binghamton Sodexo username:"
read username
echo "Enter your password:"
read -s password
echo "Running..."
java -jar ./bingMealPlanData_phantom.jar $username $password

#Rscript C:/Program\ Files/Git/HackRPI2015/linegraph.R

