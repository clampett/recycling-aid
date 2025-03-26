package src;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

import src.gui.Gui;
import src.recycling_types.*;
import src.recycling_types.materials.*;

public class RecycleDriver {
    public static void main(String[] args) {
        Loader.wipe_file("src/data/serialized/material.txt", Gui.L);
    }
}