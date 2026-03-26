package org.sijoexercise;

import org.sijoexercise.exceptions.BadInputsException;
import org.sijoexercise.helpers.SpacialAgencyCreator;

public class Main {
    public static void main(String[] args) throws BadInputsException {

        var spacialAgency = SpacialAgencyCreator.getSpacialAgency();
        spacialAgency.moveAllDrones();

    }
}