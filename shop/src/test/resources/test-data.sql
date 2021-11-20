insert into categories (id, category, description)
values (default, 'laptop', 'description for laptop category');
insert into categories (id, category, description)
values (default, 'desktop', 'description for desktop category');

insert into roles (id, role)
values (default, 'USER');
insert into roles (id, role)
values (default, 'ADMIN');

insert into address (id, city, street, house_number, floor, apartment_number)
values (default, 'Moscow', 'Sudostroitelnaya street', '12/1', 5, '112');
insert into address (id, city, street, house_number, floor, apartment_number)
values (default, 'New-York', 'Prospect Ave', '581', 1, '5C');

insert into product (id, category_id, name, description, price, active)
values (default, 1, 'Lenovo', 'lenovo laptop', 120.0, true);
insert into product (id, category_id, name, description, price, active)
values (default, 1, 'Macbook pro', 'Apple laptop 16"', 250.0, true);