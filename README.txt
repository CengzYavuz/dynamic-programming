🗺️ DP Tour Planner
DP Tour Planner is a Java-based application that generates an optimal city‑tour route for a single tourist, maximizing adjusted attractiveness scores under time constraints. It was developed as a coursework solution for the “Dynamic Programming” assignment in the CME 4436 – Basics of Internet of Things course at Dokuz Eylül University.

📝 Problem Definition
You work at a travel agency and need to design a city‑tour plan that:

Starts and ends at the Hotel (a fixed vertex in the city graph).

Visits each landmark at most once (Hotel is visited exactly twice).

Maximizes total attractiveness, where each landmark’s base score is adjusted by visitor load and by the tourist’s personal interest.

Respects travel times on each street edge.

The city is modeled as a graph:

Vertices are landmarks (with names and base attractiveness).

Edges represent streets (with travel‑time weight).

Input files provide:

Landmark data: base score & inter‑landmark travel times (landmark_map_data.txt)

Current visitor loads (visitor_load.txt)

Tourist’s personal interest ratings (personal_interest.txt)

🚀 Features
Dynamic Programming

Uses a DP approach to avoid O(n!) brute force.

Computes maximum adjusted‑score route ending back at the Hotel.

Score Adjustment

Visitor load reduces base attractiveness (crowd effect).

Personal interest scales each landmark’s appeal.

Flexible Input

Reads three UTF‑8 text files; supports arbitrary landmark sets and weights.

New datasets (with different names, scores, times) are picked up at runtime.

Output

Optimal visiting order (including Hotel start/end).

Total adjusted attractiveness.

Total travel time in minutes.

🛠️ Installation & Run
Clone the repository

bash
Copy
Edit
git clone https://github.com/your‑org/dp‑tour‑planner.git
cd dp‑tour‑planner
Import into IntelliJ IDEA

Open IntelliJ IDEA, select Open, then choose the project folder.

Ensure JDK 11 or higher is configured.

Command‑Line Build & Run (Optional)

Compile

bash
Copy
Edit
javac -d out src/**/*.java
Run

bash
Copy
Edit
java -cp out com.yourpackage.Main landmarks.txt visitor_load.txt personal_interest.txt
Place the input files in the project root or specify full paths.

📂 Code Structure
src/
├── com/yourpackage/
│ ├── Main.java • Entry point; parses arguments & runs the DP solver
│ ├── Graph.java • Graph representation (landmarks, edges)
│ ├── Landmark.java • Holds name, base score, load, interest
│ ├── DPPlanner.java • Core DP algorithm to find the optimal route
│ └── util/
│ └── FileReader.java • Parses input text files into data structures

⚙️ Algorithm Complexity
Time: O(n·2ⁿ) for n landmarks (Hotel + others), typical for TSP‑style DP.

Space: O(n·2ⁿ) for DP memoization table.

🔮 Future Improvements
Heuristic Initialization: Use greedy or branch‑and‑bound to seed the DP.

Parallelization: Distribute subproblems across multiple threads.

GUI Frontend: Visualize routes on a map API (e.g., Google Maps).

Variable Time Budgets: Allow users to specify maximum tour duration.

📧 Contact
Your Name
Email: your.email@ogr.deu.edu.tr

Happy touring! 🚴‍♂️
