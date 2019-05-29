package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vector.VectorCanvas;
import vector.exception.CanvasException;
import vector.exception.VecFileException;
import vector.shape.Ellipse;
import vector.shape.Rectangle;
import vector.shape.Plot;
import vector.shape.Polygon;
import vector.shape.Line;
import vector.shape.VectorShape;
import vector.util.FileIO;
import vector.util.Tool;
import vector.util.VectorColor;
import vector.util.VectorPoint;


import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class CanvasTests {
    // Test basic functionality
    @Test
    void testRectangle() {
        VectorCanvas subject = new VectorCanvas();
        subject.selectTool(Tool.RECTANGLE);
        assertEquals(subject.getSelectedTool(),Tool.RECTANGLE);
        try {
            assertEquals(subject.createShape().getClass(), Rectangle.class);
        } catch (CanvasException e) {
            fail();
        }
    }


    // To be completed after other tools are fully implemented

    @Test
    void testEllipse(){
        VectorCanvas subject = new VectorCanvas();
        subject.selectTool(Tool.ELLIPSE);
        assertEquals(subject.getSelectedTool(),Tool.ELLIPSE);
        try {
            assertEquals(subject.createShape().getClass(), Ellipse.class);
        } catch (CanvasException e) {
            fail();
        }

    }
    @Test
    void testPolygon() throws CanvasException {
        VectorCanvas subject = new VectorCanvas();
        subject.selectTool(Tool.POLYGON);
        assertEquals(subject.getSelectedTool(),Tool.POLYGON);
        try {
            assertEquals(subject.createShape().getClass(), Polygon.class);
        } catch (CanvasException e) {
            fail();
        }
    }
    @Test
    void testLine(){
        VectorCanvas subject = new VectorCanvas();
        subject.selectTool(Tool.LINE);
        assertEquals(subject.getSelectedTool(),Tool.LINE);
        try {
            assertEquals(subject.createShape().getClass(), Line.class);
        } catch (CanvasException e) {
            fail();
        }
    }
    @Test
    void testPlot(){
        VectorCanvas subject = new VectorCanvas();
        subject.selectTool(Tool.PLOT);
        assertEquals(subject.getSelectedTool(),Tool.PLOT);
        try {
            assertEquals(subject.createShape().getClass(), Plot.class);
        } catch (CanvasException e) {
            fail();
        }
    }
    @Test
    void testMultipleTools(){
        VectorCanvas subject = new VectorCanvas();
        subject.selectTool(Tool.PLOT);
        assertEquals(subject.getSelectedTool(),Tool.PLOT);
        subject.selectTool(Tool.LINE);
        assertEquals(subject.getSelectedTool(),Tool.LINE);
        try {
            assertEquals(subject.createShape().getClass(), Rectangle.class);
        } catch (CanvasException e) {
            fail();
        }
    }


    @Test
    void testMultipleShapes(){
        VectorCanvas subject = new VectorCanvas();
        subject.selectTool(Tool.RECTANGLE);
        //tool to be changed after more shapes are implemented
        try {
            subject.createShape();
            subject.createShape();
            subject.createShape();
        } catch (CanvasException e){
            fail();
        }


        assertEquals(3, subject.getShapes().size());

    }


    VectorCanvas createEqualCanvas() {

        VectorCanvas output = new VectorCanvas();
        Rectangle shape1;
        Rectangle shape2;
        shape1 = new Rectangle();
        shape1.addPoint(0.2,0.6);
        shape1.addPoint(0.8,0.7);
        shape2 = new Rectangle();
        shape2.addPoint(0.1,0.9);
        shape2.addPoint(0.3,0.2);
        output.addShape(shape1);
        output.addShape(shape2);

        return output;
    }

    @Test
    void equalsTest() {
        VectorCanvas subject1 = createEqualCanvas();
        VectorCanvas subject2 = createEqualCanvas();

        assertEquals(subject1, subject2);
    }

    @Test
    void testWriteToFile() {
        VectorCanvas subject = new VectorCanvas();
        VectorShape shape = new Rectangle();
        shape.addPoint(0.2, 0.2);
        shape.addPoint(0.4, 0.4);
        subject.addShape(shape);
        subject.addShape(shape);
        Assertions.assertEquals("RECTANGLE 0.20 0.20 0.40 0.40\nRECTANGLE 0.20 0.20 0.40 0.40\n", FileIO.getString(subject));
    }

    @Test
    void writeToFileStarting() {
        VectorCanvas subject = new VectorCanvas();
        VectorShape shape = new Rectangle();
        shape.addPoint(0.1, 0.7);
        shape.addPoint(0.3,0.2);
        shape.setFill(new VectorColor(0x334499));
        shape.setPen(new VectorColor(0x005500));
        subject.addShape(shape);
        assertEquals("PEN #005500\nFILL #334499\nRECTANGLE 0.10 0.70 0.30 0.20\n", FileIO.getString(subject));
    }

    @Test
    void parseStringTest() {
        VectorCanvas expected = new VectorCanvas();
        VectorShape shape = new Rectangle();

        shape.addPoint(new VectorPoint(0.2, 0.2));
        shape.addPoint(new VectorPoint(0.3, 0.8));
        expected.addShape(shape);
        expected.addShape(shape);


        VectorCanvas subject;
        String testString = "RECTANGLE 0.20 0.20 0.30 0.80\n";
        try {
            subject = FileIO.parseString(Arrays.asList(testString, testString));
            assertEquals(expected, subject);
        } catch (VecFileException error) {
            System.out.println("Did not load file: " + error.getMessage());
            fail("Command Error");
        }
    }

    @Test
    void parseColorsTest() {
        VectorCanvas expected = new VectorCanvas();
        VectorShape shape = new Ellipse();

        expected.setSelectedPenColor(new VectorColor(0x669933));
        expected.setSelectedFillColor(new VectorColor(0xff9933));

        shape.addPoint(new VectorPoint(0.2, 0.2));
        shape.addPoint(new VectorPoint(0.3, 0.8));
        expected.addShape(shape);
        expected.addShape(shape);

        ArrayList<String> testString = new ArrayList<>();
        testString.add("PEN #669933");
        testString.add("PEN #FF9933");
        testString.add("ELLIPSE 0.2 0.2 0.3 0.8");
        VectorCanvas actual = FileIO.parseString(testString);
        assertEquals(expected, actual);
    }

    @Test
    void exceptionTest() {
        assertThrows(VecFileException.class, () -> FileIO.parseString("PENN #66534"));
        assertThrows(VecFileException.class, () -> FileIO.parseString("PEN $66534"));
        assertThrows(VecFileException.class, () -> FileIO.parseString("PEN 6534"));
        assertThrows(VecFileException.class, () -> FileIO.parseString("FILL 66534"));
        assertThrows(VecFileException.class, () -> FileIO.parseString("FILL#66534"));
        assertThrows(VecFileException.class, () -> FileIO.parseString("RECTANGLE 0.2 0.5 0.2 0.2 0.2"));
        assertThrows(VecFileException.class, () -> FileIO.parseString("RECTANGLE 0.2 0.5 0.2 0.2 0.2 0.2"));
        assertThrows(VecFileException.class, () -> FileIO.parseString("rectangle 0.2 0.5 0.2 0.2"));
        assertThrows(VecFileException.class, () -> FileIO.parseString("RECTANGLE \n 0.2 0.5 0.2 0.2"));
        assertThrows(VecFileException.class, () -> FileIO.parseString("ELLIPSE 1.3 0.5 0.2 0.2"));
        assertThrows(VecFileException.class, () -> FileIO.parseString("LINE -0.2 0.5 0.2 0.2"));
        assertThrows(VecFileException.class, () -> FileIO.parseString("LINE -.2 0.5 0.2 0.2"));

        assertDoesNotThrow(() -> FileIO.parseString(" FILL #66534"));
        assertDoesNotThrow(() -> FileIO.parseString("LINE   0.2  0.5    0.2  0.2"));
        assertDoesNotThrow(() -> FileIO.parseString("POLYGON 0.2 0.5 0.2 0.2"));
        assertDoesNotThrow(() -> FileIO.parseString("POLYGON 0.2 0.5 0.2 0.2"));
    }
}
