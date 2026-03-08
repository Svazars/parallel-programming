### #1
**Answer:** `~1.11x` (11% speedup)

We use Amdahl's Law formula for overall speedup:
$$S = \frac{1}{(1 - p) + \frac{p}{s}}$$
Where $p$ represents the proportion of time spent on the portion of the code where improvements are made (0.1), and $s$ represents the extent of the improvement
 (10 000).
$$S = \frac{1}{(1 - 0.10) + \frac{0.10}{10000}}$$
$$S = \frac{1}{0.90 + 0.00001} = \frac{1}{0.90001} \approx 1.11109$$

### #2
**Answer:** `5 CPUs`

We use the execution time formula derived from Amdahl's law:
$$T = T_{serial} + \frac{T_{parallel}}{s}$$
Where $T$ is the target time (100 minutes), $T_{serial}$ is the time of the serial part A (50 minutes), $T_{parallel}$ is the time of the parallel part B on a single CPU (250 minutes), and $s$ is the number of CPUs.
$$100 = 50 + \frac{250}{s}$$
$$50 = \frac{250}{s}$$
$$s = 5$$

### #3
**Answer:** A program where `90.9%` of the execution time is parallelizable, and `9.1%` is serial.

$S = 10$, $s = 100$:
$$S = \frac{1}{(1 - p) + \frac{p}{s}}$$
$$10 = \frac{1}{(1 - p) + \frac{p}{100}}$$
$$10 \cdot (1 - p + 0.01p) = 1$$
$$10 - 9.9p = 1$$
$$9.9p = 9$$
$$p = \frac{9}{9.9} \approx 0.909$$