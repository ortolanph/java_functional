import argparse
import json
import random


def chanceOfDelivery():
    return bool(random.getrandbits(1))


def generate_revenue_data(profile):
    with open(f"detailed_data_profile_{profile}.json", "r") as definitions:
        profile_defintions = json.load(definitions)

        countries = profile_defintions["countries"]
        sections = profile_defintions["sections"]
        revenue_range_min = int(profile_defintions["revenue_range_min"])
        revenue_range_max = int(profile_defintions["revenue_range_max"])

        definitions.close()

    months = [
        "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"
    ]

    country_data = []

    for country in countries:
        store_data = []

        for store_number in range(1, country["stores"] + 1):
            location = country["cities"][random.randint(0, len(country["cities"]) - 1)]
            store_id = f"{country["symbol"]}{location[0:3].upper()}{store_number:03d}"

            section_data = []

            for section in sections:
                section_data.append(
                    {
                        'section': section,
                        'revenues': [
                            {"month": month, "revenue": round(random.uniform(revenue_range_min, revenue_range_max), 2)} for month in months
                        ]
                    }
                )

            store_data.append({
                "id": store_id,
                "employees": int(random.uniform(country["minEmployees"], country["maxEmployees"])),
                "location": location,
                "delivery": chanceOfDelivery(),
                "sections": section_data
            })

        country_data.append({
            "name": country["name"],
            "stores": store_data
        })

    market_data = {
        "name": "EDG Market",
        "year": 2024,
        "countries": country_data
    }

    return market_data


def main(profile):
    data = generate_revenue_data(profile)

    with open(f"supermarket_revenue_detailed_{profile}.json", "w") as handler:
        json.dump(data, handler, indent=4)

    print(f"Revenue data generated and saved to 'supermarket_revenue_detailed_{profile}.json'.")


if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Generate data for testing")
    parser.add_argument("-p", "--profile", help="The profile could be prod or test.", default="prod")

    args = parser.parse_args()

    main(args.profile)
