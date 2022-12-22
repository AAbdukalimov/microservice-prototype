USE solva;
CREATE TABLE IF NOT EXISTS month_limit (
id bigint primary key auto_increment,
starting_date date,
ending_date datetime,
expense_category varchar(30),
currency varchar(20),
sum_limit double
);

drop table month_limit;