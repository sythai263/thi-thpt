SHELL := /bin/bash
NAME = Thi_THPT
.PHONY: start
BUILD_NAME := AppThiTHPT.jar

bootstrap:
	cp .env.example .env

up:
	source .env && docker compose up -d

down:
	source .env && docker compose down

start-app:
	(set -a && source .env && ./gradlew start-app)