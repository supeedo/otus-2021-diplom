insert into categories (id, category, description)
values (default, 'laptop', 'description for laptop category');
insert into categories (id, category, description)
values (default, 'desktop', 'description for desktop category');

insert into roles (id, role)
values (default, 'USER');
insert into roles (id, role)
values (default, 'ADMIN');

insert into user_address (id, city, street, house_number, floor, apartment_number)
values (default, 'Moscow', 'Sudostroitelnaya street', '12/1', 5, '112');
insert into user_address (id, city, street, house_number, floor, apartment_number)
values (default, 'New-York', 'Prospect Ave', '581', 1, '5C');

insert into product (id, category_id, name, description, price, active)
values (default, 1, 'Lenovo', 'lenovo laptop', 120.0, true);
insert into product (id, category_id, name, description, price, active)
values (default, 1, 'Macbook pro', 'Apple laptop 16"', 250.0, true);

insert into orders (id, create_time, delivery_time, user_id, note, status_id)
values (default, '2021-10-19 10:23:54+03', '2021-12-30 23:30:00+03', 1, 'call in an hour', 1);
insert into orders (id, create_time, delivery_time, user_id, note, status_id)
values (default, '2021-10-19 10:23:54+03', '2021-12-30 23:30:00+03', 1, 'call in an hour', 1);