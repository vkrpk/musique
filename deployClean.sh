#!/bin/bash
mvn clean package -X
mvn cargo:redeploy