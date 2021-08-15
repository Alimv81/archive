import tkinter
import phonenumbers
from tkinter import messagebox
from phonenumbers import geocoder, carrier

frame = tkinter.Tk()
label = tkinter.Label(frame, text="enter your number :: ", fg="#000000", bg="#2256A6")
entry = tkinter.Entry(frame)
button = tkinter.Button(frame, text="Next")


def menuList(event):
    myMenu.tk_popup(event.x_root, event.y_root)


def run():
    error = False
    country = str()
    operator = str()
    number = "+" + str(entry.get())
    try:
        country = geocoder.description_for_number(phonenumbers.parse(number), "en")
        operator = carrier.name_for_number(phonenumbers.parse(number), "en")
    except phonenumbers.NumberParseException:
        error = True
        messagebox.showerror("wrong number", "please enter a valid number")
    finally:
        entry.delete(0, tkinter.END)
        if not error:
            messagebox.showinfo("numbers place", "country :: {} \noperator :: {}".format(country, operator))


button.configure(command=lambda: run())
myMenu = tkinter.Menu(frame, tearoff=False)
myMenu.add_command(label="next", command=run)
myMenu.add_command(label="bye")
myMenu.add_separator()
myMenu.add_command(label="exit", command=frame.quit)
frame.bind("<Button-3>", menuList)
label.place(x=0, y=0)
entry.place(x=150, y=0)
button.place(x=300, y=80)
button.configure(bg="#973310")
frame.configure(bg="#5C1097", height=500, width=500)
frame.title("phone trace")
frame.geometry("380x150+600+300")
frame.resizable(False, False)
frame.mainloop()
