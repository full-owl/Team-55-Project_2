import datetime
import random

def create_sale(date):
    sub_total = 0

    return sub_total

def create_date(year, month, day):
    return "'"+ str(datetime.date(2022, 10, 12))+"'"

def main():
    order_file = open("orders.csv", "a")
    order_item_file = open("order_items.csv", "a")
    
    # order_file.write("id,subtotal,total,employee_id\n")
    # order_item_file.write("order_id,quantity,meal_type,entree1,entree2,entree3,side1,side2,custom_instructions\n")
    
    
    order_file.close()
    order_item_file.close()

    

main()