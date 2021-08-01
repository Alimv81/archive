from phonenumbers import geocoder, parse, carrier

number = "+982636770058"

ch_number = parse(number, "CH")
print(geocoder.description_for_number(ch_number, "en"))

service = parse(number, "RO")
print(carrier.name_for_number(service, "en"))
