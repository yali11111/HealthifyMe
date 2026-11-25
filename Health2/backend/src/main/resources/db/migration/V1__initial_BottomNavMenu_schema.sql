-- ==================================================
-- BottomNavMenu - DATABASE
-- V1 : INITIAL SCHEMA
-- PostgreSQL
-- ==================================================

-- ==================================================
-- USERS
-- ==================================================

CREATE TABLE users (

    id UUID PRIMARY KEY,

    name VARCHAR(100) NOT NULL,

    email VARCHAR(254) NOT NULL UNIQUE,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP

);

CREATE INDEX idx_users_email
ON users(email);


-- ==================================================
-- STEPS
-- ==================================================

CREATE TABLE steps (

    id UUID PRIMARY KEY,

    user_id UUID NOT NULL REFERENCES users(id)
        ON DELETE CASCADE,

    steps INTEGER NOT NULL CHECK (steps >= 0),

    goal INTEGER CHECK (goal >= 0),

    distance DOUBLE PRECISION CHECK (distance >= 0),

    calories_burned DOUBLE PRECISION
        CHECK (calories_burned >= 0),

    recorded_at TIMESTAMPTZ NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP

);

CREATE INDEX idx_steps_user_date
ON steps(user_id, recorded_at DESC);


-- ==================================================
-- WEIGHT
-- ==================================================

CREATE TABLE weight (

    id UUID PRIMARY KEY,

    user_id UUID NOT NULL REFERENCES users(id)
        ON DELETE CASCADE,

    weight DOUBLE PRECISION NOT NULL
        CHECK (weight > 0 AND weight <= 500),

    target_weight DOUBLE PRECISION
        CHECK (target_weight > 0 AND target_weight <= 500),

    unit VARCHAR(10) NOT NULL DEFAULT 'kg'
        CHECK (unit IN ('kg', 'lb')),

    recorded_at TIMESTAMPTZ NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP

);

CREATE INDEX idx_weight_user_date
ON weight(user_id, recorded_at DESC);


-- ==================================================
-- CALORIES
-- ==================================================

CREATE TABLE calories (

    id UUID PRIMARY KEY,

    user_id UUID NOT NULL REFERENCES users(id)
        ON DELETE CASCADE,

    basal_metabolic_rate DOUBLE PRECISION
        CHECK (basal_metabolic_rate >= 0),

    daily_calories DOUBLE PRECISION
        CHECK (daily_calories >= 0),

    calories_consumed DOUBLE PRECISION
        CHECK (calories_consumed >= 0),

    calories_burned DOUBLE PRECISION
        CHECK (calories_burned >= 0),

    recorded_at TIMESTAMPTZ NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP

);

CREATE INDEX idx_calories_user_date
ON calories(user_id, recorded_at DESC);


-- ==================================================
-- SLEEP
-- ==================================================

CREATE TABLE sleep (

    id UUID PRIMARY KEY,

    user_id UUID NOT NULL REFERENCES users(id)
        ON DELETE CASCADE,

    total_duration INTEGER NOT NULL
        CHECK (total_duration >= 0),

    deep_sleep INTEGER NOT NULL DEFAULT 0
        CHECK (deep_sleep >= 0),

    light_sleep INTEGER NOT NULL DEFAULT 0
        CHECK (light_sleep >= 0),

    rem_sleep INTEGER NOT NULL DEFAULT 0
        CHECK (rem_sleep >= 0),

    awake_duration INTEGER NOT NULL DEFAULT 0
        CHECK (awake_duration >= 0),

    sleep_efficiency DOUBLE PRECISION
        CHECK (sleep_efficiency BETWEEN 0 AND 100),

    recorded_at TIMESTAMPTZ NOT NULL,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT sleep_duration_consistency
    CHECK (
        deep_sleep + light_sleep + rem_sleep
        <= total_duration
    )

);

CREATE INDEX idx_sleep_user_date
ON sleep(user_id, recorded_at DESC);


-- ==================================================
-- SUGGESTIONS
-- ==================================================

CREATE TABLE suggestions (

    id UUID PRIMARY KEY,

    user_id UUID NOT NULL REFERENCES users(id)
        ON DELETE CASCADE,

    title VARCHAR(150) NOT NULL,

    description TEXT NOT NULL,

    category VARCHAR(50) NOT NULL,

    priority INTEGER NOT NULL DEFAULT 0,

    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP

);

CREATE INDEX idx_suggestions_user_date
ON suggestions(user_id, created_at DESC);
