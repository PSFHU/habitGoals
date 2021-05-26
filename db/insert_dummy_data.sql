-- Aimlab goals
-- First we create a main_goal
insert into main_goal (main_goal_id,title)
values (20,'Aimlab Professional');

-- Second we create measure_fields
insert into measure_field (measure_field_id, title)
values (1,'Gridshot Precision');
insert into measure_field (measure_field_id, title)
values (2,'Spidershot');
insert into measure_field (measure_field_id, title)
values (3,'Microshot');

-- Third we setup our side_goals
insert into side_goal (main_goal_id, measure_field_id, title, goal_value)
values (20,1,'Gridshot Precision 65000 point',65000);
insert into side_goal (main_goal_id, measure_field_id, title, goal_value)
values (20,2,'Spidershot 64000 point',64000);
insert into side_goal (main_goal_id, measure_field_id, title, goal_value)
values (20,3,'Microshot 70000 point',70000);

-- Forth we give in some measure_values
insert into measure_value(measure_field_id, value, time_stamp)
values (1,50000,'2021-03-10');
insert into measure_value(measure_field_id, value, time_stamp)
values (1,52000,'2021-03-11');
insert into measure_value(measure_field_id, value, time_stamp)
values (1,54000,'2021-03-12');
insert into measure_value(measure_field_id, value, time_stamp)
values (1,51000,'2021-03-13');
insert into measure_value(measure_field_id, value, time_stamp)
values (2,40021,'2021-03-10');
insert into measure_value(measure_field_id, value, time_stamp)
values (2,44021,'2021-03-11');
insert into measure_value(measure_field_id, value, time_stamp)
values (2,42021,'2021-03-12');
insert into measure_value(measure_field_id, value, time_stamp)
values (2,45021,'2021-03-13');
insert into measure_value(measure_field_id, value, time_stamp)
values (3,60021,'2021-03-12');
insert into measure_value(measure_field_id, value, time_stamp)
values (3,71498,'2021-03-13');

-- Workout goals
-- First we create a main_goal
insert into main_goal (main_goal_id,title)
values (21,'Summer body by Saitama');

-- Second we create measure_fields
insert into measure_field (measure_field_id, title)
values (4,'Push-ups');
insert into measure_field (measure_field_id, title)
values (5,'Sit-ups');
insert into measure_field (measure_field_id, title)
values (6,'Squats');
insert into measure_field (measure_field_id, title)
values (7,'Running(km)');

-- Third we setup our side_goals
insert into side_goal (main_goal_id, measure_field_id, title, goal_value)
values (21,4,'Do 100 Push-ups',100);
insert into side_goal (main_goal_id, measure_field_id, title, goal_value)
values (21,5,'Do 100 Sit-ups',100);
insert into side_goal (main_goal_id, measure_field_id, title, goal_value)
values (21,6,'Do 100 Squats',100);
insert into side_goal (main_goal_id, measure_field_id, title, goal_value)
values (21,7,'Do 10km Running',10);

-- Forth we give in some measure_values
insert into measure_value(measure_field_id, value, time_stamp)
values (4,10,'2021-03-10');
insert into measure_value(measure_field_id, value, time_stamp)
values (4,15,'2021-03-11');
insert into measure_value(measure_field_id, value, time_stamp)
values (4,15,'2021-03-12');
insert into measure_value(measure_field_id, value, time_stamp)
values (4,20,'2021-03-13');
insert into measure_value(measure_field_id, value, time_stamp)
values (5,20,'2021-03-10');
insert into measure_value(measure_field_id, value, time_stamp)
values (5,25,'2021-03-11');
insert into measure_value(measure_field_id, value, time_stamp)
values (5,25,'2021-03-12');
insert into measure_value(measure_field_id, value, time_stamp)
values (5,35,'2021-03-13');
insert into measure_value(measure_field_id, value, time_stamp)
values (6,20,'2021-03-10');
insert into measure_value(measure_field_id, value, time_stamp)
values (6,30,'2021-03-11');
insert into measure_value(measure_field_id, value, time_stamp)
values (6,40,'2021-03-12');
insert into measure_value(measure_field_id, value, time_stamp)
values (7,1,'2021-03-10');
insert into measure_value(measure_field_id, value, time_stamp)
values (7,2,'2021-03-12');
insert into measure_value(measure_field_id, value, time_stamp)
values (7,2,'2021-03-13');
