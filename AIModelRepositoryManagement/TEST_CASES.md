# Test Cases

| Test ID | Input | Expected Output | Actual Output | Status |
|---|---|---|---|---|
| TC-01 | Admin login (admin/admin123) | Login successful | Login successful | Pass |
| TC-02 | Dev registration (DEV002) | Developer registered | Developer registered | Pass |
| TC-03 | Add AI Model (AI002) | Model added successfully | Model added successfully | Pass |
| TC-04 | Duplicate Model ID | Model ID already exists error | Model ID already exists error | Pass |
| TC-05 | Search "TensorFlow" | Displays TF models | Displays TF models | Pass |
| TC-06 | Update Model Accuracy | Accuracy updated | Accuracy updated | Pass |
| TC-07 | Delete Model (AI002) | Model deleted | Model deleted | Pass |
| TC-08 | Invalid Model ID | Model not found error | Model not found error | Pass |
| TC-09 | Invalid Accuracy (-5) | Error: between 0 and 100 | Error: between 0 and 100 | Pass |
| TC-10 | Add model version (1.1) | Version added | Version added | Pass |
| TC-11 | View version history | Displays versions list | Displays versions list | Pass |
| TC-12 | Generate report | Displays categorical summary | Displays categorical summary | Pass |
| TC-13 | Invalid Menu Choice (99) | Error: Invalid choice | Error: Invalid choice | Pass |
| TC-14 | Empty Model Name | Error: cannot be empty | Error: cannot be empty | Pass |
| TC-15 | Logout | Successfully logged out | Successfully logged out | Pass |
