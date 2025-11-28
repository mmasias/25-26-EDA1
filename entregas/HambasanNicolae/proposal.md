# RCCCF Kitchen Simulation – Proposal

## 1. Problem Analysis

The RCCCF kitchen presents an interesting and perhaps somewhat puzzling scenario. Orders arrive at random intervals, specifically with a 40% probability each minute and not entirely predictable but not completely chaotic either. Each order comes with a preparation time ranging from something trivial like a drink taking one or two minutes, to more substantial items like a salad taking up to eight minutes.  

The cook, in theory, should always pick the order with the **shortest remaining time**. This makes sense intuitively efficiency is the goal after all but one could argue whether constantly reorganizing priorities might itself incur some cost. Are we really saving time by this strategy? Perhaps, perhaps not, but for now we accept it as the rule.  

So at the heart of this problem is the task of **repeatedly extracting the minimum element** from a collection that is always changing. New orders arrive old orders complete dynamically and unpredictably. And yet we must track everything, and somehow count the number of comparisons because efficiency metrics are requested.

## -- Short Idea --
A very important question rises as what if we would do the exact opposite? What if, instead of taking the order with the shortest preparation time we would do vice-versa and prioritize the order with the longest prep time? Or what if we create a system based on time counting and instead of prioritizing one or another we do both! Quick exemple would be that for every 10 minutes of work, for 5 we prioritize the shortest prep time and for the rest the longest prep time!. Putting this into a simulation would sure be fun and maybe even create a new way of managing efficiency!

---

## 2. Possible Data Structures

Several options come to mind, each with its own quirks and caveats:

 Structure                   Advantages                                      Disadvantages / Efficiency                       
|-----------------------------|-----------------------------------------------|-------------------------------------------------|
 **ArrayList / List**         Simple -- easy to understand                           Finding the minimum requires scanning the whole list — O(n)  
 **PriorityQueue / Min-Heap** Efficient extraction O(log n),insertion O(log n);    A bit complicated and it might feel like overkill for a small simulation;
 **Sorted Linked List**       Min is always at the head, extraction O(1)            Maintaining sorted order on insertion costs O(n), so maybe we are just shifting the complexity 
 **Unsorted Linked List**     Insertion is trivial, O(1)                            Extraction of min still O(n) and we need more pointer management.

Arguebly, the ArrayList is perfectly adequate. Yes, it is inefficient in a theoretical sense, but it is **transparent**, and importantly, it allows us to **count comparisons manually** which is very valuable(for me at least). For a small-scale simulation this seems the best way even if the queue became enormous and the performance might not be at it's best.  

**Decision for this proposal:**  
- Use **ArrayList**.  
- Purpose: clarity and explicit comparison counting. Efficiency is secondary but still tracked.

---

## 3. Conceptual Diagram

The conceptual flow is relatively straightforward but I put down in mind the following image:
 
     New Order  
   (random type) 
 
          │
          ▼
  
    Waiting Queue   <- ArrayList of Orders
          │
          ▼
  
         Cook 
    - Picks order   
     with min time 
    - Processes 1 min
  
           │
     Order completed
           │
           ▼

      Update stats  
       - Served      
       - Waiting time
       - Comparisons 
  




In practice there are different approaches. For instance should we update waiting times **before** or **after** selecting the next order? It seems logical to update after, but the waiting times for the remaining orders accumulate anyway regardless of which is chosen next. But maybe these small details are irrelevant in our simulation(at least I hope so).

---

## 4. Implementation Plan

- **Classes / Modules**
  1. `Order`: stores type, total time, and remaining time. Simple enough but there’s always a question thought: do we need more state? Perhaps tracking arrival time would make statistics more precise. But for now we stick to the basics.  
  2. `Kitchen`: manages the queue, selects the order with minimum time, processes orders, and updates statistics. Counting comparisons explicitly.  
  3. `Simulation`: loops minute by minute, checks for new orders, calls `Kitchen` methods. Pretty simple but I keep wondering if there are small details I might miss.

- **Key Operations**
  1. Insert new orders into the queue.  
  2. Scan the queue to **find the order with minimum remaining time**, incrementing a comparison counter.  
  3. Process the selected order minute by minute.  
  4. Update statistics: served orders, total and average waiting times, pending orders, and number of comparisons.  

- **Output**
  - Real-time minute-by-minute status. 
  - End-of-day summary: total served, pending, total and average waiting times, comparisons.  

It is worth noting that while this is the proposed plan I feel that the method is simple and a little too naive. There might be better and more elegant ways(for sure) or at least a more clever priority structure but they would obscure my logic so I am against it. 

---

**Conclusion / Reflection**  

This proposal is very simple but with acknowledgement of potential inefficiencies and problems. It favors **clarity and traceability over raw performance** which seems appropriate given the objectives. And while I could endlessly debate optimizations, the ArrayList-based approach captures the essence of repeatedly extracting the minimum in a dynamic system while allowing explicit comparison counting the very metric the exercise seeks to illuminate.




