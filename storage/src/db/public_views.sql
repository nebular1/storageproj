create or replace view products_v as
select
    p.id,
    p.product_name,
    p.product_size,
    p.product_price,
    p.product_date
from products p;

create or replace view income_v as
select
    i.id,
    i.product_name,
    i.product_size,
    i.product_price,
    i.product_date
from income i;

create or replace view outcome_v as
select
    o.id,
    o.product_name,
    o.product_size,
    o.product_price,
    o.product_date
from outcome o;