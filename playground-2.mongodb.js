// Switch to the USER_DATA database
use('USER_DATA');

// Insert a user document
db.users.insertOne({
    email: "ctvreyes11@gmail.com",
    password_hash: "Javier2026", // Remember to hash passwords
    created_at: new Date(),
    email_verified: false,
    account_status: "active"
});

// Create unique indexes (only need to do this once)
db.users.createIndex({ "email": 1 }, { unique: true });
// Verify the insert worked
db.users.find();