CREATE TABLE main_goal
(
    main_goal_id        serial PRIMARY KEY,
    title               varchar(255) NOT NULL
);

CREATE TABLE measure_field(
    measure_field_id    serial PRIMARY KEY,
    title               varchar(255) NOT NULL
);

CREATE TABLE side_goal(
    id                  serial PRIMARY KEY,
    main_goal_id        serial NOT NULL,
    measure_field_id    serial NOT NULL,
    title               varchar(255) NOT NULL,
    goal_value          double precision,
    CONSTRAINT fk_main_goal
        FOREIGN KEY(main_goal_id)
            REFERENCES main_goal(main_goal_id),
    CONSTRAINT fk_measure_field_id
        FOREIGN KEY(measure_field_id)
            REFERENCES measure_field(measure_field_id)
);

CREATE TABLE measure_value(
    id                  serial PRIMARY KEY,
    measure_field_id    serial NOT NULL,
    value               double precision,
    time_stamp          date,
    CONSTRAINT fk_measure_field
        FOREIGN KEY(measure_field_id)
            REFERENCES measure_field(measure_field_id)
);