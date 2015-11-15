#!/bin/bash

echo "Enter your Binghamton Sodexo username:"
read username
echo "Enter your password:"
read -s password
echo "Running..."
java -jar ./bingMealPlanData.jar $username $password

