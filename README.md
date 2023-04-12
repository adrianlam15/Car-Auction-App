# Car Auction House

### | **Introduction**
Welcome to the Car Auction House application, a middleman platform for buyers and sellers to participate in car auctions. 
The application allows users to create listings for cars they would like to auction and bid on cars they would like to buy. The car will be sold to the highest bid after the countdown for the auction has ended.

### | **Features**
- Create listings for cars to be auctioned
- Place bids on cars for sale
- Browse listings by category (exotic, old, vintage, Japanese imports, etc.)
- Easy-to-use interface for buying and selling cars

### | **Target Users**
This application is designed for individuals who are interested in buying or selling cars from a range of categories. It is perfect for those who want to purchase a specific car but have been unable to due to the complications of online auction houses and the lack in
options when shopping locally.

### | **Why this project is important**
This project is important to me as a developer because I want to create an application that simplifies the process of buying and selling second-hand cars. The goal is to make the experience of participating in car auctions more seamless and user-friendly for everyone involved.

### | **Tech Stack**
This application will be built using Java.

### | **User Stories / functionality**
- As a user, I want to be able to list a car up for auction with a minimum bid and time interval.
- As a user, I want to be able to make bids on a car
- As a user, I want to be able to buy the car after the auction time interval has ended, and I have the highest bid.
- As a user, I want to be able to 'import' my current user state of my listings and bids from a file to the application.
- As a user, I want to be able to 'export' my current user state of my listings and bids to a file from the application.
- As a user, I want to be able to add multiple Cars to 

### | **Instructions for Grader** 
- The user is able to generate the first of the two required actions that are related to the required user story by logging in with an account (you can create an account if you choose, or use the one I've made for you in this format, username:password, 210grader:enjoythisgui). Once you've logged in, you'll see a "Create Listing" button in the top left, once you get to this state, fill in the required details, and press create to create the listing. Press the "View Listing" button to navigate to the "View Listings" panel to see your newly created car.
- The user must be logged in, in order to generate the second of the two required actions. The user is able to choose to delete listings from the "View Listings" panel, or alternatively "View Your Listings" panel.
- The visual component that was added to my proejct can be found on the home screen of the application.
- The user can save the state of the application to file after logging in, then there will be a "Save" button in the bottom left to save the current state of the application.
- The user can load the state of the application to file after logging in, then there will be a "Load" button in the bottom left to load the previous state of the application.
- **IMPORTANT**: The music may be annoying so on the login page there is a small square button underneath the "sign up" button which can be clicked to toggle mute the song (if you enjoy the song though its from Initial D, ZA-ZA - GIVE ME LOVE AND MONEY)

### | **Phase 4: Task 2**
- An example of the event logs:
```
Mon Apr 10 06:57:03 PDT 2023: Bid placed for $1000
Mon Apr 10 06:57:03 PDT 2023: User Adrian2 placed bid on car: [Good condition] Automatic Blue Nissan Skyline R34 GTR, FWD; with 200000km for $1000.
	Time remaining: 99555 seconds.
Mon Apr 10 06:57:07 PDT 2023: User Adrian logged in
```

### | **Phase 4: Task 3 / Future implementations**
If I had more time to work on the project, I would try to implement the Singleton design pattern, the Observer design pattern, and further refactor common code through a helper function.
First I would implement the Singleton design pattern by making only one possible instance of each of the subclasses that extend UiState, as there does not need to be multiple instances of them.
Second, I would implement the Observer design pattern by making the UiState the observer and each of the subclasses of UiState a subject, so that I am able to push updates to a certain
UI state just from the UiState class. Currently, the program updates UI states by reinitializing classes and JPanels.