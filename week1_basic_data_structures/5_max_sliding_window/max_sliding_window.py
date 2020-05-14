# python3
from collections import deque

def max_sliding_window_naive(sequence, m):
    maximums = []
    deq = deque()
    l = len(sequence)
    for i in range(l):
        # print(deq)
        while len(deq) != 0 and sequence[deq[-1]] < sequence[i]:
            deq.pop()
        deq.append(i)

        if i >= m-1 :
            maximums.append(sequence[deq[0]])
            if i-m+1 == deq[0]:
                deq.popleft()
        # print(maximums)
    return maximums

if __name__ == '__main__':
    n = int(input())
    input_sequence = [int(i) for i in input().split()]
    assert len(input_sequence) == n
    window_size = int(input())

    print(*max_sliding_window_naive(input_sequence, window_size))

