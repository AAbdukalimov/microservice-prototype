USE solva;
CREATE TABLE IF NOT EXISTS currency_rate(
    id bigint primary key auto_increment,
    symbol varchar(20),
    datetime datetime,
    close double,
    previous_close double,
    is_market_open boolean
);

drop table currency_rate;
