Jeg kendte ikke Arrays.toString() metoden, så lærte jeg også noget i dag :-)

---

Denne navngivning 

    sum_of_dice

er JavaScript måden at navngive variabler på, I Java bruger vi camelCase

    sumOfDice

---

I equal_eyes metoden har I en variable I ikke bruger

    final int dice_arr_length = two_dice_array.length; // array length calculated in variable

---

I samme metode kan 

    if(two_dice_array[0] == two_dice_array[1]){
        return true;
    }
    return false;

erstattes med 

    return two_dice_array[0] == two_dice_array[1]

---

Jeres occurrence metode kan implementeres med 

    occurrence_array[two_dice_array[0] - 1]++;
    occurrence_array[two_dice_array[1] - 1]++;

---
