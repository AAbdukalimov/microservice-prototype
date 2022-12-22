CREATE DATABASE IF NOT EXISTS solva;

CREATE TABLE IF NOT EXISTS transaction
(
    id                     bigint primary key auto_increment,
    date_time              date,
    account_from           bigint,
    account_to             bigint,
    currency_shortname    varchar(20),
    sum                    double,
    expense_category       varchar(30),
    transaction_sum_in_usd double,
    limit_exceeded         boolean
);


CREATE TABLE IF NOT EXISTS month_limit (
                                           id bigint primary key auto_increment,
                                           starting_date date,
                                           ending_date datetime,
                                           expense_category varchar(30),
                                           currency varchar(20),
                                           sum_limit double
);


CREATE TABLE IF NOT EXISTS currency_rate(
                                            id bigint primary key auto_increment,
                                            symbol varchar(20),
                                            datetime datetime,
                                            close double,
                                            previous_close double,
                                            is_market_open boolean
);