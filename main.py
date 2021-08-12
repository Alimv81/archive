import tkinter
import phonenumbers
from phonenumbers import geocoder, carrier


def run(number):
    number = "+" + str(number)
    my_country = geocoder.description_for_number(phonenumbers.parse(number), "en")
    my_operator = carrier.name_for_number(phonenumbers.parse(number), "en")

    file = open("data.txt", "w")
    file.write(my_country)
    file.write("\n")
    file.write(my_operator)


frame = tkinter.Tk()

label = tkinter.Label(frame, text="enter your number :: ", fg="#000000", bg="#2256A6")
entery = tkinter.Entry(frame)
button = tkinter.Button(frame, text="Next")


def button_action():
    run(entery.get())
    file = open("data.txt", "r")
    objects = list(i for i in file)
    frame.destroy()
    frame2 = tkinter.Tk()
    tkinter.Label(frame2, text="country :: ").grid(row=0, column=0)
    tkinter.Label(frame2, text=objects[0][:-1]).grid(row=0, column=1)
    tkinter.Label(frame2, text="operator").grid(row=1, column=0)
    tkinter.Label(frame2, text=objects[1]).grid(row=1, column=1)
    frame2.title("place")
    frame2.configure(bg="#104997")
    frame2.geometry("150x150")
    frame2.mainloop()


button.configure(command=button_action)
label.grid(row=0, column=0)
entery.grid(row=0, column=1)
button.grid(row=1, column=2)
frame.configure(bg="#568483", height=500, width=500)
frame.title("phone trace")
frame.mainloop()
