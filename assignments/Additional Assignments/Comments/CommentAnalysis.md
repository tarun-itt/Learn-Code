
1.  **Redundant Comments**: 
    *   `// This method processes an order`
    *   `// Check if order is null`
    *   `// Validate the order`
    *   `// Check inventory`
    *   `// If no inventory, return failure`
    *   `// Reserve inventory`
    *   `// Process payment`
    *   `// Check if payment succeeded`
    *   `// Update inventory`
    *   `// Send confirmation email`
    *   `// Return success`
    *   `// Payment failed, release inventory`
    *   `// Return failure`
    *   `// Throw it`
    *   `// Log the error`
    *   `// Get the order`
    *   `// Refund the payment`
    *   `// Give back the items`
    *   `// Update status`
    *   `// Gets order by ID`
    *   `// Saves the order`

2.  **Noise Comments**: 
    *   `// Something went wrong`
    *   `// Implementation here` 

3.  **Journal Comments**: 
    *   `// Added by John on 12/15/2023 - needed for the new feature`

4.  **Attribution/Hearsay**: 
    *   `// John says we need to refund here`

5.  **Marker Comments**: 
    *   `// This is important!!!`

6.  **TODO Comments**:
    *   `// TODO: Fix this later`

## Reflection
The original usage of comments created a lot of redundant comments, where the reading the code itself is sufficeint. Some Todo comments were necessary which have been added in the refactored code. Also the code did not follow proper SRP so even that has been refactored
Now after removing unneccessary comments and refactoring the code is more readable and maintainable.