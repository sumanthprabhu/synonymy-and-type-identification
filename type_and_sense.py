import sys

from nltk.corpus import wordnet as wn
from collections import Counter

def main(argv):
	
	with open(argv[1], "rb") as input_data:

		term = input_data.readline().strip()
		neighbours = input_data.readlines()

		candidates = []

		for neighbour in neighbours:
			neighbour = neighbour.strip()

			candidates.append(
				x.lowest_common_hypernyms(y) for x in wn.synsets(term) \
				for y in wn.synsets(neighbour)
				)

		candidates = [val for sublist in candidates for val in sublist]
		candidates = [val for sublist in candidates for val in sublist]

		counts = Counter(candidates)
		candidates = sorted(candidates, key=counts.get, reverse= True)

		if candidates:
			print candidates[0].lemma_names()
		
if __name__ == "__main__":
	main(sys.argv)
