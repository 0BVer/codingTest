package main

import (
	"bufio"
	"os"
	"sort"
	"strconv"
)

var graph map[int16][]int16
var visited []bool
var best []int16
var count int16
var size, link int
var max int16 = 0
var a, b int16

var sc *bufio.Scanner
var wr *bufio.Writer

func init() {
	sc = bufio.NewScanner(os.Stdin)
	sc.Split(bufio.ScanWords)
	wr = bufio.NewWriter(os.Stdout)
}

func main() {
	defer wr.Flush()
	size = scanInt()
	link = scanInt()

	graph = make(map[int16][]int16)
	for i := 0; i < link; i++ {
		a = int16(scanInt())
		b = int16(scanInt())
		graph[b] = append(graph[b], a)
	}
	best = make([]int16, size)
	best = nil
	for i := range graph {
		if graph[i] == nil {
			continue
		}
		visited = make([]bool, size+1)
		count = 0
		dfs(i)
		if count > max {
			max = count
			best = nil
			best = append(best, i)
		} else if count == max {
			best = append(best, i)
		}
	}
	sort.Slice(best, func(i, j int) bool {
		return i < j
	})
	for _, n := range best {
		_, _ = wr.WriteString(strconv.Itoa(int(n)) + " ")
	}
}

func dfs(index int16) {
	visited[index] = true
	count++
	for _, value := range graph[index] {
		if !visited[value] {
			dfs(value)
		}
	}
}

func scanInt() (n int) {
	sc.Scan()
	n, _ = strconv.Atoi(sc.Text())
	return
}
