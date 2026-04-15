import os
import re

# This script will look at every PNG in every subfolder
# and rename it to Prefix_000.png, Prefix_001.png, etc.

root_dir = "." # Runs in the current folder

for subdir, dirs, files in os.walk(root_dir):
    # Filter for png files and sort them to keep animation order
    png_files = sorted([f for f in files if f.lower().endswith('.png')])
    
    if not png_files:
        continue

    print(f"Processing folder: {subdir}")

    for index, filename in enumerate(png_files):
        # Extract the prefix (everything before the last underscore or number)
        # Example: "WalkLeft_01.png" -> Prefix is "WalkLeft"
        prefix_match = re.match(r"([a-zA-Z]+)_?", filename)
        if prefix_match:
            prefix = prefix_match.group(1)
        else:
            prefix = "frame"

        # Create the new name: Prefix_000.png (3-digit padding)
        new_name = f"{prefix}_{index:03d}.png"
        
        old_path = os.path.join(subdir, filename)
        new_path = os.path.join(subdir, new_name)

        # Rename the file
        os.rename(old_path, new_path)

print("Done! All frames are now 3-digit padded and start at 000.")