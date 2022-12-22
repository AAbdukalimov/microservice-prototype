USE solva;
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
drop table transaction;