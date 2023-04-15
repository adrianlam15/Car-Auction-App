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

### | **Event Logs**
- An example of the event logs:
```
Mon Apr 10 06:57:03 PDT 2023: Bid placed for $1000
Mon Apr 10 06:57:03 PDT 2023: User Adrian2 placed bid on car: [Good condition] Automatic Blue Nissan Skyline R34 GTR, FWD; with 200000km for $1000.
	Time remaining: 99555 seconds.
Mon Apr 10 06:57:07 PDT 2023: User Adrian logged in
```

### | **Future implementations**
- Implement Singleton design pattern to make only one possible instance of each of the subclasses that extend UiState, as there does not need to be multiple instances of them.
- Implement Observer design pattern by making the UiState the observer and each of the subclasses of UiState a subject, to push updates to a certain
UI state just from the UiState class. Currently, the program updates UI states by reinitializing classes and JPanels.
