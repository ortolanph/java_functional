import random
from datetime import datetime, timedelta
from uuid import uuid4

import avro.io
import avro.schema

ACTIONS = ["CREATE", "UPDATE", "DELETE"]

SYSTEMS = ["THX1138", "LUV2234", "HAT2334", "VOD0000"]

NAMES = [
    'Joshua', 'Donna', 'Susan', 'Matthew', 'Mary', 'Sandra', 'Emily', 'Karen',
    'Daniel', 'Donald', 'Patricia', 'Jennifer', 'Elizabeth', 'Edward', 'Ashley',
    'Robert', 'Joseph', 'Christopher', 'Andrew', 'Lisa', 'James', 'Steven',
    'Sarah', 'Michelle', 'Margaret', 'Kimberly', 'William', 'Anthony', 'Thomas',
    'Nancy', 'Mark', 'John', 'Charles', 'Barbara', 'Michael', 'Jessica', 'Paul',
    'Linda', 'David', 'Betty'
]

SURNAMES = [
    "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller",
    "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez",
    "Wilson", "Anderson", "Thomas", "Taylor", "Moore", "Jackson", "Martin",
    "Lee", "Perez", "Thompson", "White", "Harris", "Sanchez", "Clark",
    "Ramirez", "Lewis", "Robinson", "Walker", "Young", "Allen", "King",
    "Wright", "Scott", "Torres", "Nguyen", "Hill", "Flores"
]

PHONE_PREFIXES = [
    "+1",  # USA, Canada
    "+44",  # United Kingdom
    "+33",  # France
    "+49",  # Germany
    "+39",  # Italy
    "+91",  # India
    "+61",  # Australia
    "+81",  # Japan
    "+86",  # China
    "+7",  # Russia
    "+34",  # Spain
    "+55",  # Brazil
    "+27",  # South Africa
    "+46",  # Sweden
    "+47",  # Norway
    "+48",  # Poland
    "+31",  # Netherlands
    "+32",  # Belgium
    "+41",  # Switzerland
    "+52",  # Mexico
    "+62",  # Indonesia
    "+63",  # Philippines
    "+64",  # New Zealand
    "+65",  # Singapore
    "+66",  # Thailand
    "+82",  # South Korea
    "+90",  # Turkey
    "+351",  # Portugal
    "+353",  # Ireland
    "+354",  # Iceland
    "+372",  # Estonia
    "+373",  # Moldova
    "+374",  # Armenia
    "+375",  # Belarus
    "+376",  # Andorra
    "+380",  # Ukraine
    "+381",  # Serbia
    "+382",  # Montenegro
    "+385",  # Croatia
]

TOTAL_MESSAGES = 10_000

SCHEMA_PATH = "schema.avsc"

SCHEMA = avro.schema.parse(open(SCHEMA_PATH, "r").read())


def main():
    message_collection = []

    for msgs in range(0, TOTAL_MESSAGES + 1):
        message_id = str(uuid4())
        action = random.choices(ACTIONS)[0]
        timestamp = datetime.today().strftime("%d/%m/%y-%H:%M:%S")

        message_header = {
            "createdBy": random.choices(SYSTEMS)[0],
            "timestamp": timestamp
        }

        message_body = {
            "id": str(uuid4()),
            "name": f"{random.choices(SURNAMES)[0]}, {random.choices(NAMES)[0]}",
            "phone": {
                "prefix": random.choices(PHONE_PREFIXES)[0],
                "number": "9" + "".join(str(random.randint(0, 9)) for _ in range(8)),
            },
            "birthdate": (datetime.now() - timedelta(days=random.randint(18 * 365, 70 * 365))).strftime("%Y-%m-%d"),
        }

        message_collection.append({
            "id": message_id,
            "action": action,
            "header": message_header,
            "body": message_body
        })

    with open("messages.avro", "wb") as avro_file:
        writer = avro.io.DatumWriter(SCHEMA)
        encoder = avro.io.BinaryEncoder(avro_file)
        writer.write(message_collection, encoder)


if __name__ == '__main__':
    main()
