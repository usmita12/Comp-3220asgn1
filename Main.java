/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstract class representing a shape.
 */
abstract class CShape {
    private static int counter = 0;
    private final int id;

    /**
     * Constructs a new shape and assigns a unique ID.
     */
    public CShape() {
        this.id = ++counter;
    }

    /**
     * Gets the unique ID of the shape.
     *
     * @return The shape's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Abstract method to get the information about the shape.
     *
     * @return A string representation of the shape.
     */
    public abstract String getInfo();
}

/**
 * Class representing an oval, which is a specific type of shape.
 */
class COval extends CShape {
    private final int horizontalRadius;
    private final int verticalRadius;

    /**
     * Constructs a new oval with the given dimensions.
     *
     * @param horizontalRadius The horizontal radius of the oval.
     * @param verticalRadius   The vertical radius of the oval.
     */
    public COval(int horizontalRadius, int verticalRadius) {
        super();
        this.horizontalRadius = horizontalRadius;
        this.verticalRadius = verticalRadius;
    }

    /**
     * Gets the information about the oval.
     *
     * @return A string representation of the oval.
     */
    @Override
    public String getInfo() {
        return String.format("OVAL %dx%d", horizontalRadius, verticalRadius);
    }

    /**
     * Gets the horizontal radius of the oval.
     *
     * @return The horizontal radius.
     */
    public int getHorizontalRadius() {
        return horizontalRadius;
    }

    /**
     * Gets the vertical radius of the oval.
     *
     * @return The vertical radius.
     */
    public int getVerticalRadius() {
        return verticalRadius;
    }
}

/**
 * Class representing a circle, which is a specific type of oval.
 */
class CCircle extends COval {
    /**
     * Constructs a new circle with the given radius.
     *
     * @param radius The radius of the circle.
     */
    public CCircle(int radius) {
        super(radius, radius);
    }

    /**
     * Gets the information about the circle.
     *
     * @return A string representation of the circle.
     */
    @Override
    public String getInfo() {
        return String.format("CIRCLE %d", getHorizontalRadius());
    }
}

/**
 * Class representing a rectangle, which is a shape with length and width.
 */
class CRectangle extends CShape {
    private final int length;
    private final int width;

    /**
     * Constructs a new rectangle with the given dimensions.
     *
     * @param length The length of the rectangle.
     * @param width  The width of the rectangle.
     */
    public CRectangle(int length, int width) {
        super();
        this.length = length;
        this.width = width;
    }

    /**
     * Gets the information about the rectangle.
     *
     * @return A string representation of the rectangle.
     */
    @Override
    public String getInfo() {
        return String.format("RECTANGLE %dx%d", length, width);
    }

    /**
     * Gets the length of the rectangle.
     *
     * @return The length.
     */
    public int getLength() {
        return length;
    }

    /**
     * Gets the width of the rectangle.
     *
     * @return The width.
     */
    public int getWidth() {
        return width;
    }
}

/**
 * Class representing a square, which is a specific type of rectangle.
 */
class CSquare extends CRectangle {
    /**
     * Constructs a new square with the given side length.
     *
     * @param sideLength The side length of the square.
     */
    public CSquare(int sideLength) {
        super(sideLength, sideLength);
    }
}

/**
 * Class representing a canvas, which "has-a" list of shapes.
 */
class CCanvas {
    private final List<CShape> shapes;

    /**
     * Constructs a new canvas with a list of shapes.
     *
     * @param shapes The list of shapes to be added to the canvas.
     */
    public CCanvas(List<CShape> shapes) {
        this.shapes = shapes;
    }

    /**
     * Gets the list of shapes on the canvas.
     *
     * @return The list of shapes.
     */
    public List<CShape> getShapes() {
        return shapes;
    }

    /**
     * Adds a shape to the canvas.
     *
     * @param shape The shape to be added.
     */
    public void addShape(CShape shape) {
        shapes.add(shape);
    }

    /**
     * Generates and adds 10 random non-duplicate shapes to the canvas.
     */
    public void generateRandomShapes() {
        Random random = new Random();
        List<Class<? extends CShape>> shapeTypes = List.of(CCircle.class, COval.class, CSquare.class, CRectangle.class);
        List<CShape> addedShapes = new ArrayList<>();

        while (addedShapes.size() < 10) {
            Class<? extends CShape> randomShapeType = shapeTypes.get(random.nextInt(shapeTypes.size()));
            CShape newShape = generateRandomShape(random, randomShapeType);

            // Ensure no duplicates
            if (!addedShapes.contains(newShape)) {
                addedShapes.add(newShape);
                addShape(newShape);
            }
        }
    }

    /**
     * Generates a random shape based on the given shape type.
     *
     * @param random       The random number generator.
     * @param shapeType    The type of shape to generate.
     * @return A randomly generated shape.
     */
    private CShape generateRandomShape(Random random, Class<? extends CShape> shapeType) {
        if (shapeType.equals(CCircle.class)) {
            return new CCircle(random.nextInt(100) + 1); // Radius between 1 and 100
        } else if (shapeType.equals(COval.class)) {
            return new COval(random.nextInt(100) + 1, random.nextInt(100) + 1); // Horizontal and vertical radius between 1 and 100
        } else if (shapeType.equals(CSquare.class)) {
            return new CSquare(random.nextInt(100) + 1); // Side length between 1 and 100
        } else if (shapeType.equals(CRectangle.class)) {
            return new CRectangle(random.nextInt(100) + 1, random.nextInt(100) + 1); // Length and width between 1 and 100
        } else {
            throw new IllegalArgumentException("Invalid shape type");
        }
    }

    /**
     * Prints the information about each shape on the canvas.
     */
    public void displayShapes() {
        System.out.println("Canvas has the following random shapes:");
        for (CShape shape : shapes) {
            System.out.printf("Shape %d: %s%n", shape.getId(), shape.getInfo());
        }
    }
}

/**
 * The main class to test the canvas and shapes.
 */
public class Main {
    /**
     * Main method to instantiate a Canvas containing 10 random non-duplicate shapes and printing them.
     *
     * @param args Command line
      */
    public static void main(String[] args) {
        // Instantiate a canvas
        CCanvas canvas = new CCanvas(new ArrayList<>());

        // Generate and display random shapes
        canvas.generateRandomShapes();
        canvas.displayShapes();
    }
}
     
 
