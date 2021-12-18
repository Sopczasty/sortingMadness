# Sorting Madness
Aplikacja służąca do sortowania zbiorów danych różnymi algorytmami. Ma pomóc użytkownikowi w ocenie, która metoda może się najlepiej sprawdzić w konkretnych przypadkach oraz, oczywiście, w uporządkowaniu danych. Dane liczbowe są sortowane standardowo, natomiast tekstowe - leksykograficznie. Należy zaimplementować co najmniej 6 różnych metod sortowania. Aplikacja będzie dostępna poprzez GUI, a także jako zdalne API, dzięki czemu można zintegrować z istniejącymi narzędziami.

![example workflow](https://github.com/Sopczasty/sortingMadness/actions/workflows/build.yml/badge.svg)

## Grupa I1-2-Beta

### Członkowie zespołu

* Julia Chabora
* Mateusz Chmielewski
* Jakub Dunajko (Proxy Product Owner)
* Maciej Kleban (Scrum Master)
* Marek Pietkiewicz

### Dobór algorytmu sortującego
Algorytm jest dobierany na podstawie poniższych kryteriów:
1. **Bubble Sort** - najszybszy algorytm jeżeli zestaw danych jest bardzo mały lub niemalże posortowany (niezależnie od rozmiaru)
2. **Insertion Sort** - szybki dla prawie posortowanego zestawu danych, małe zużycie pamięci : O(1)
3. **Selection Sort** - szybki dla małych zestawów danych bez względu na uszeregowanie, nie wymaga dodatkowej pamięci na dane
4. **Heap Sort** - bardzo dobry jeżeli użytkownikowi zależy na tym, aby najgorszy scenariusz nie był złożony (zawsze O(nlogn))
5. **Quick Sort** - ma złożoność pamięciową O(logn), ale w średnim przypadku jest najszybszy, w najbardziej pesymistycznym O(n^2)
6. **Merge Sort** - zawsze złożoność obliczeniowa O(nlogn), lecz bardzo duża złożoność pamięciowa O(n), co sprawia że nie jest dobry dla bardzo dużych zestawów danych
