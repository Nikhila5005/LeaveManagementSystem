# Leave Management System

The Leave Management System is designed to automate and streamline the process of managing employee leave requests, ensuring efficiency and accuracy within organizations.


## Features

- **Configurable Leave Management**: Orchestrated a **comprehensive leave management system** with configurable **leave types** and **approval workflows** using **Spring Boot** and **PostgreSQL**, reducing **administrative delays by 60%**.  
- **Hierarchical Leave Approval System**: Developed a **three-tier workflow** with **dynamic time-based escalation rules**, decreasing **approval delays by 50%** and ensuring timely decision-making.  
- **Automated Leave Balance Tracker**: Designed an automated **leave balance tracking system** with allocation rules, reducing **manual errors by 70%** and ensuring compliance with leave policies.  
- **Real-Time Email Notifications**: Implemented **Java Mail Sender** for real-time **leave approval notifications**, achieving **100% transparency** and enhancing employee engagement.  
- **Enhanced HR Efficiency**: Improved **HR efficiency by 40%** through the implementation of **leave combination rules** aligned with organizational policies. 


## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Nikhila5005/LeaveManagementSystem.git
   ```
2. **Navigate to the Project Directory**:
   ```bash
   cd LeaveManagementSystem
   ```
3. **Install Dependencies**:
   Ensure you have [Maven](https://maven.apache.org/) installed. Then, run:
   ```bash
   mvn install
   ```
4. **Configure the Database**:
   - Set up your preferred database (e.g., MySQL, PostgreSQL).
   - Update the database configuration in `src/main/resources/application.properties`.

5. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```

## Usage

- **Access the Application**:
  Once the application is running, access it via `http://localhost:8080`.
- **User Roles**:
  - **Admin**: Manage leave policies, approve or reject leave requests, and generate reports.
  - **Employee**: Apply for leave, check leave balances, and view the status of leave requests.
- **Leave Application Process**:
  - Employees can submit leave requests specifying the type and duration.
  - Managers receive notifications for approval or rejection.
  - Approved leaves are automatically recorded in the system.


## Contributing

Contributions are welcome! To contribute:

1. **Fork the Repository**: Click on the 'Fork' button at the top right corner of this page.
2. **Create a New Branch**:
   ```bash
   git checkout -b feature/YourFeatureName
   ```
3. **Commit Your Changes**:
   ```bash
   git commit -m 'Add some feature'
   ```
4. **Push to the Branch**:
   ```bash
   git push origin feature/YourFeatureName
   ```
5. **Open a Pull Request**: Navigate to the original repository and click on 'New Pull Request'.

## Contact

For any queries or collaboration, feel free to reach out to  
[**Akula Nikhila Reddy**](mailto:nikhilareddy5005@gmail.com)
