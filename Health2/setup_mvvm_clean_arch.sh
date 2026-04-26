#!/bin/bash

APP_NAME="BottomNavMenu"

echo "🚀 Setting up Advanced MVVM Clean Architecture structure..."

mkdir -p app/src/main/java/com/example/$APP_NAME

BASE="app/src/main/java/com/example/$APP_NAME"

# =========================
# Presentation Layer
# =========================
mkdir -p $BASE/presentation/ui/activities
mkdir -p $BASE/presentation/ui/fragments
mkdir -p $BASE/presentation/ui/adapters
mkdir -p $BASE/presentation/viewmodel

# =========================
# Domain Layer
# =========================
mkdir -p $BASE/domain/model
mkdir -p $BASE/domain/repository
mkdir -p $BASE/domain/usecase

# =========================
# Data Layer
# =========================
mkdir -p $BASE/data/repository
mkdir -p $BASE/data/local/db/dao
mkdir -p $BASE/data/local/db/entity
mkdir -p $BASE/data/local/assets
mkdir -p $BASE/data/remote/api
mkdir -p $BASE/data/remote/model
mkdir -p $BASE/data/mapper

# =========================
# Core
# =========================
mkdir -p $BASE/core/utils
mkdir -p $BASE/core/constants
mkdir -p $BASE/core/dispatcher

# =========================
# DI (Hilt)
# =========================
mkdir -p $BASE/di

# =========================
# Services
# =========================
mkdir -p $BASE/services

# =========================
# Create placeholder files
# =========================

touch $BASE/presentation/ui/activities/MainActivity.java
touch $BASE/presentation/ui/fragments/HomeFragment.java
touch $BASE/presentation/ui/fragments/AddFragment.java
touch $BASE/presentation/ui/fragments/ViewFragment.java

touch $BASE/presentation/viewmodel/HomeViewModel.java
touch $BASE/presentation/viewmodel/StepsViewModel.java

touch $BASE/domain/model/Card.java
touch $BASE/domain/repository/UserStatsRepository.java
touch $BASE/domain/usecase/GetStepsUseCase.java

touch $BASE/data/repository/UserStatsRepositoryImpl.java
touch $BASE/data/local/db/AppDatabase.java
touch $BASE/data/local/db/dao/UserStatsDao.java
touch $BASE/data/local/db/entity/UserStatsEntity.java

touch $BASE/data/remote/api/ApiService.java

touch $BASE/di/AppModule.java
touch $BASE/di/DatabaseModule.java
touch $BASE/di/RepositoryModule.java

touch $BASE/core/utils/DataParser.java

touch $BASE/services/StepsCounterService.java

echo "✅ MVVM Clean Architecture structure created successfully!"
echo "📦 Root path: $BASE"
