import datetime
import random

sides = ["Chow Mein", "Chow Fun", "Fried Rice", "Brown Steamed Rice", "White Steamed Rice", "Super Greens"]
entrees = ["The Original Orange Chicken", "Grilled Teriyaki Chicken", "Broccoli Beef", "Kung Pao Chicken", "Honey Sesame Chicken Breast", "Beijing Beef", "Mushroom Chicken", "SweetFire Chicken Breast", "String Bean Chicken Breast", "Black Pepper Chicken", "Super Greens"]
prem_entrees = ["Beyond The Original Orange Chicken", "Black Pepper Angus Steak", "Honey Walnut Shrimp"]
app = ["Chicken Egg Roll", "Veggie Spring Roll", "Cream Cheese Rangoon"]
drinks = ["Fountain cup", "Dr Pepper","Coca Cola","Diet Coke","Barq's Root Beer", "Fanta Orange", "Minute Maid Lemonade", "Powerade Mountain Berry Blast", "Sprite", "Fuze Raspberry Iced Tea", "Passion Mango Black Tea", "Sweet Tea", "Powerade Fruit Punch", "Dasani", "Minute Maid Apple Juice", "Coca Cola", "Coke Mexico", "Coke Zero", "Bai Coco Fusion", "Sanzo Lychee Sparkling Water" ]

prices = {
    "bowl": 7.50,
    "plate": 9.00,
    "bigger plate": 10.50,
    "family": 32.00
}
prices_side = {
    "medium": 4.40,
    "large": 5.40
}
prices_entree = {
    "small": 4.90,
    "medium": 8.90,
    "large": 10.40
}

prices_premium_entree = {
    "small": 6.15,
    "medium": 10.70,
    "large": 10.40
}

prices_drink = {
    "small": 2.10,
    "medium": 2.30,
    "large": 2.50
}

prices_app = { 
    "small": 2.00,
    "large": 10.00
}


def create_bowl_sale(date, id): # enter into orders and order items
    
    sub_total = 0
    sub_total += prices["bowl"]

    sub_total += prices_drink["medium"]
    # id,date,subtotal,total,employee_id
    order_file = open("orders.csv", "a")
    order_file.write(str(id) + ","+ date + "," + str(round(sub_total,2)) + "," + str(round(sub_total *(1+.0825),2)) + "," + "1\n")
    order_file.close()
    #order_id,meal_type,entree1,entree2,entree3,side1,side2,custom_instructions
    order_item_file = open("order_items.csv", "a")
    order_item_file.write(str(id) + ",bowl," + str(random.randint(7, 17)) + ",null,null," +str(random.randint(1, 6)) + ",null,null\n")
    order_item_file.write(str(id) + ",drink,null,null,null,null,null,medium\n")
    order_item_file.close()
    return sub_total

def create_plate_sale(date, id):
    sub_total = 0
    sub_total += prices["plate"]
    
    sub_total += prices_drink["large"]
    order_file = open("orders.csv", "a")
    order_file.write(str(id) + ","+ date + "," + str(round(sub_total,2)) + "," + str(round(sub_total *(1+.0825),2)) + "," + "1\n")
    order_file.close()

    order_item_file = open("order_items.csv", "a")
    order_item_file.write(str(id) + ",plate," + str(random.randint(7, 17)) + "," + str(random.randint(7, 17)) + ",null," +str(random.randint(1, 6)) + ",null,null\n")
    order_item_file.write(str(id) + ",drink,null,null,null,null,null,large\n")
    order_item_file.close()


    return sub_total
def create_family_sale(date,id):
    sub_total = 0
    sub_total += prices["family"]
    
    sub_total += prices_drink["medium"]
    sub_total += prices_drink["medium"]
    sub_total += prices_drink["medium"]
    sub_total += prices_drink["medium"]
    order_file = open("orders.csv", "a")
    order_file.write(str(id) + ","+ date + "," + str(round(sub_total,2)) + "," + str(round(sub_total *(1+.0825),2)) + "," + "1\n")
    order_file.close()

    order_item_file = open("order_items.csv", "a")
    order_item_file.write(str(id) + ",family," + str(random.randint(7, 17)) + "," + str(random.randint(7, 17)) + "," +str(random.randint(7, 17))+","+str(random.randint(1, 6)) + ",null,null\n")
    order_item_file.write(str(id) + ",drink,null,null,null,null,null,medium\n")
    order_item_file.write(str(id) + ",drink,null,null,null,null,null,medium\n")
    order_item_file.write(str(id) + ",drink,null,null,null,null,null,medium\n")
    order_item_file.write(str(id) + ",drink,null,null,null,null,null,medium\n")
    
    order_item_file.close()


    return sub_total

def create_meal(date, id):
    choice = random.randint(0,2)
    if choice == 0:
        return create_bowl_sale(date, id)
    elif choice == 1:
        return create_plate_sale(date, id)
    else:
        return create_family_sale(date, id)

def create_date(year, month, day):
    return str(datetime.date(year, month, day))

def main():
    # order_file = open("orders.csv", "a")
    # order_item_file = open("order_items.csv", "a")
    total = 15000/7
    running_total = 0
    print("total:", total)
    # order_file.write("id,date,subtotal,total,employee_id\n")
    # order_item_file.write("order_id,quantity,meal_type,entree1,entree2,entree3,side1,side2,custom_instructions\n")
    id = 1869

    current_sales = 0
    for i in range(17, 23):
        current_sales = 0
        while (current_sales < total):
            if i == 8:
                total = 15000/7 + random.randint(3000,7050)
            else:
                total = 15000/7
            current_sales += round(create_meal(create_date(2022, 10, i), id),2)
            id += 1
        running_total += round(current_sales,2)

    # TODO round sale to two decimal points
    print("current_total:", running_total)
    print("latest id:", id)
    # order_file.close()
    # order_item_file.close()

    

main()