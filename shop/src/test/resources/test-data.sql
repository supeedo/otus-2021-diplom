
insert into category (id, category, description)
values (default, 'laptop', 'description for laptop category');
insert into category (id, category, description)
values (default, 'desktop', 'description for desktop category');

insert into roles (id, role)
values (default, 'ROLE_USER');
insert into roles (id, role)
values (default, 'ROLE_ADMIN');

insert into status_orders (id, status)
values (default, 'REGISTRATION');
insert into status_orders (id, status)
values (default, 'DELIVERY');

insert into user_address (id, city, street, house_number, floor, apartment_number)
values (default, 'Moscow', 'Sudostroitelnaya street', '12/1', 5, '112');
insert into user_address (id, city, street, house_number, floor, apartment_number)
values (default, 'New-York', 'Prospect Ave', '581', 1, '5C');

insert into users (id, email, password, user_info_id, role_id, active)
values (default, 'test@test-host.ru', 'qwerty', 1, 1, true);
insert into users (id, email, password, user_info_id, role_id, active)
values (default, 'test2@test-host.ru', 'ytrewq', 2, 1, true);

insert into user_informations (id, first_name, last_name, patronymic, phone, user_address_id)
values (default, 'Ivanov', 'Ivan', 'Ivanovich', '88001231212', '1');
insert into user_informations (id, first_name, last_name, patronymic, phone, user_address_id)
values (default, 'Jimmy', 'Alish', null, 88003213232, '1');

insert into products (id, category_id, name, description, price, active)
values (default, 1, 'Lenovo', 'lenovo laptop', 120.0, true);
insert into products (id, category_id, name, description, price, active)
values (default, 1, 'Macbook pro', 'Apple laptop 16"', 250.0, true);

insert into orders (id, user_id, note, status_id)
values (default, 1, 'some first test notes', 1);
insert into orders (id, user_id, note, status_id)
values (default, 1, 'some second test notes', 1);

insert into order_items (id, order_id, product_id, product_count)
values (default, 1, 1, 15);
insert into order_items (id, order_id, product_id, product_count)
values (default, 1, 1, 25);

