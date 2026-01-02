import json
from pathlib import Path

# Base file
base_file = Path("moisture_compass_01.json")

# Load the base JSON
with base_file.open("r", encoding="utf-8") as f:
  base_data = json.load(f)

# Generate files moisture_compass_02.json -> moisture_compass_31.json
for i in range(2, 32):
  suffix = f"{i:02d}"

  # Deep copy via JSON round-trip to be safe
  data = json.loads(json.dumps(base_data))

  # Update texture reference
  data["textures"]["layer0"] = f"more_useful_copper:item/moisture_compass_{suffix}"

  # Output file
  output_file = Path(f"moisture_compass_{suffix}.json")
  with output_file.open("w", encoding="utf-8") as f:
    json.dump(data, f, indent=2)

  print(f"Created {output_file}")
