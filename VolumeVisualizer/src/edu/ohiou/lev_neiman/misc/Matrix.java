package edu.ohiou.lev_neiman.misc;

public class Matrix
{
    public Matrix()
    {
        for( int i = 0; i < 16; ++i )
        {
            if( i == 0 || i == 5 || i == 10 || i == 15 )
            {
                matrix[ i ] = 1;
            }
            else
            {
                matrix[ i ] = 0;
            }
        }
    }

    float matrix[] = new float[16 ];

    float[] multiply( float[] v )
    {
        float ret[] = new float[4 ];

        ret[ 0 ] = matrix[ 0 ] * v[ 0 ] + matrix[ 4 ] * v[ 1 ] + matrix[ 8 ] * v[ 2 ] + matrix[ 12 ] * v[ 3 ];
        ret[ 1 ] = matrix[ 1 ] * v[ 0 ] + matrix[ 5 ] * v[ 1 ] + matrix[ 9 ] * v[ 2 ] + matrix[ 13 ] * v[ 3 ];
        ret[ 2 ] = matrix[ 2 ] * v[ 0 ] + matrix[ 6 ] * v[ 1 ] + matrix[ 10 ] * v[ 2 ] + matrix[ 14 ] * v[ 3 ];
        ret[ 3 ] = matrix[ 3 ] * v[ 0 ] + matrix[ 7 ] * v[ 1 ] + matrix[ 11 ] * v[ 2 ] + matrix[ 15 ] * v[ 3 ];
        return ret;

    }

    void inverse()
    {
        float t[] = new float[16 ];
        GenerateInverseMatrix4f( t, matrix );
        matrix = null;
        matrix = t;
    }

    public static double Determinant4f( double m[] )
    {
        return
                m[ 12 ] * m[ 9 ] * m[ 6 ] * m[ 3 ] -
                m[ 8 ] * m[ 13 ] * m[ 6 ] * m[ 3 ] -
                m[ 12 ] * m[ 5 ] * m[ 10 ] * m[ 3 ] +
                m[ 4 ] * m[ 13 ] * m[ 10 ] * m[ 3 ] +
                m[ 8 ] * m[ 5 ] * m[ 14 ] * m[ 3 ] -
                m[ 4 ] * m[ 9 ] * m[ 14 ] * m[ 3 ] -
                m[ 12 ] * m[ 9 ] * m[ 2 ] * m[ 7 ] +
                m[ 8 ] * m[ 13 ] * m[ 2 ] * m[ 7 ] +
                m[ 12 ] * m[ 1 ] * m[ 10 ] * m[ 7 ] -
                m[ 0 ] * m[ 13 ] * m[ 10 ] * m[ 7 ] -
                m[ 8 ] * m[ 1 ] * m[ 14 ] * m[ 7 ] +
                m[ 0 ] * m[ 9 ] * m[ 14 ] * m[ 7 ] +
                m[ 12 ] * m[ 5 ] * m[ 2 ] * m[ 11 ] -
                m[ 4 ] * m[ 13 ] * m[ 2 ] * m[ 11 ] -
                m[ 12 ] * m[ 1 ] * m[ 6 ] * m[ 11 ] +
                m[ 0 ] * m[ 13 ] * m[ 6 ] * m[ 11 ] +
                m[ 4 ] * m[ 1 ] * m[ 14 ] * m[ 11 ] -
                m[ 0 ] * m[ 5 ] * m[ 14 ] * m[ 11 ] -
                m[ 8 ] * m[ 5 ] * m[ 2 ] * m[ 15 ] +
                m[ 4 ] * m[ 9 ] * m[ 2 ] * m[ 15 ] +
                m[ 8 ] * m[ 1 ] * m[ 6 ] * m[ 15 ] -
                m[ 0 ] * m[ 9 ] * m[ 6 ] * m[ 15 ] -
                m[ 4 ] * m[ 1 ] * m[ 10 ] * m[ 15 ] +
                m[ 0 ] * m[ 5 ] * m[ 10 ] * m[ 15 ];
    }

    public static boolean GenerateInverseMatrix4f( double i[], double m[] )
    {
        double x = Determinant4f( m );
        if( x == 0 )
        {
            return false;
        }

        i[ 0 ] = ( -m[ 13 ] * m[ 10 ] * m[ 7 ] + m[ 9 ] * m[ 14 ] * m[ 7 ] + m[ 13 ] * m[ 6 ] * m[ 11 ]
                   - m[ 5 ] * m[ 14 ] * m[ 11 ] - m[ 9 ] * m[ 6 ] * m[ 15 ] + m[ 5 ] * m[ 10 ] * m[ 15 ] ) / x;
        i[ 4 ] = ( m[ 12 ] * m[ 10 ] * m[ 7 ] - m[ 8 ] * m[ 14 ] * m[ 7 ] - m[ 12 ] * m[ 6 ] * m[ 11 ]
                   + m[ 4 ] * m[ 14 ] * m[ 11 ] + m[ 8 ] * m[ 6 ] * m[ 15 ] - m[ 4 ] * m[ 10 ] * m[ 15 ] ) / x;
        i[ 8 ] = ( -m[ 12 ] * m[ 9 ] * m[ 7 ] + m[ 8 ] * m[ 13 ] * m[ 7 ] + m[ 12 ] * m[ 5 ] * m[ 11 ]
                   - m[ 4 ] * m[ 13 ] * m[ 11 ] - m[ 8 ] * m[ 5 ] * m[ 15 ] + m[ 4 ] * m[ 9 ] * m[ 15 ] ) / x;
        i[ 12 ] = ( m[ 12 ] * m[ 9 ] * m[ 6 ] - m[ 8 ] * m[ 13 ] * m[ 6 ] - m[ 12 ] * m[ 5 ] * m[ 10 ]
                    + m[ 4 ] * m[ 13 ] * m[ 10 ] + m[ 8 ] * m[ 5 ] * m[ 14 ] - m[ 4 ] * m[ 9 ] * m[ 14 ] ) / x;
        i[ 1 ] = ( m[ 13 ] * m[ 10 ] * m[ 3 ] - m[ 9 ] * m[ 14 ] * m[ 3 ] - m[ 13 ] * m[ 2 ] * m[ 11 ]
                   + m[ 1 ] * m[ 14 ] * m[ 11 ] + m[ 9 ] * m[ 2 ] * m[ 15 ] - m[ 1 ] * m[ 10 ] * m[ 15 ] ) / x;
        i[ 5 ] = ( -m[ 12 ] * m[ 10 ] * m[ 3 ] + m[ 8 ] * m[ 14 ] * m[ 3 ] + m[ 12 ] * m[ 2 ] * m[ 11 ]
                   - m[ 0 ] * m[ 14 ] * m[ 11 ] - m[ 8 ] * m[ 2 ] * m[ 15 ] + m[ 0 ] * m[ 10 ] * m[ 15 ] ) / x;
        i[ 9 ] = ( m[ 12 ] * m[ 9 ] * m[ 3 ] - m[ 8 ] * m[ 13 ] * m[ 3 ] - m[ 12 ] * m[ 1 ] * m[ 11 ]
                   + m[ 0 ] * m[ 13 ] * m[ 11 ] + m[ 8 ] * m[ 1 ] * m[ 15 ] - m[ 0 ] * m[ 9 ] * m[ 15 ] ) / x;
        i[ 13 ] = ( -m[ 12 ] * m[ 9 ] * m[ 2 ] + m[ 8 ] * m[ 13 ] * m[ 2 ] + m[ 12 ] * m[ 1 ] * m[ 10 ]
                    - m[ 0 ] * m[ 13 ] * m[ 10 ] - m[ 8 ] * m[ 1 ] * m[ 14 ] + m[ 0 ] * m[ 9 ] * m[ 14 ] ) / x;
        i[ 2 ] = ( -m[ 13 ] * m[ 6 ] * m[ 3 ] + m[ 5 ] * m[ 14 ] * m[ 3 ] + m[ 13 ] * m[ 2 ] * m[ 7 ]
                   - m[ 1 ] * m[ 14 ] * m[ 7 ] - m[ 5 ] * m[ 2 ] * m[ 15 ] + m[ 1 ] * m[ 6 ] * m[ 15 ] ) / x;
        i[ 6 ] = ( m[ 12 ] * m[ 6 ] * m[ 3 ] - m[ 4 ] * m[ 14 ] * m[ 3 ] - m[ 12 ] * m[ 2 ] * m[ 7 ]
                   + m[ 0 ] * m[ 14 ] * m[ 7 ] + m[ 4 ] * m[ 2 ] * m[ 15 ] - m[ 0 ] * m[ 6 ] * m[ 15 ] ) / x;
        i[ 10 ] = ( -m[ 12 ] * m[ 5 ] * m[ 3 ] + m[ 4 ] * m[ 13 ] * m[ 3 ] + m[ 12 ] * m[ 1 ] * m[ 7 ]
                    - m[ 0 ] * m[ 13 ] * m[ 7 ] - m[ 4 ] * m[ 1 ] * m[ 15 ] + m[ 0 ] * m[ 5 ] * m[ 15 ] ) / x;
        i[ 14 ] = ( m[ 12 ] * m[ 5 ] * m[ 2 ] - m[ 4 ] * m[ 13 ] * m[ 2 ] - m[ 12 ] * m[ 1 ] * m[ 6 ]
                    + m[ 0 ] * m[ 13 ] * m[ 6 ] + m[ 4 ] * m[ 1 ] * m[ 14 ] - m[ 0 ] * m[ 5 ] * m[ 14 ] ) / x;
        i[ 3 ] = ( m[ 9 ] * m[ 6 ] * m[ 3 ] - m[ 5 ] * m[ 10 ] * m[ 3 ] - m[ 9 ] * m[ 2 ] * m[ 7 ]
                   + m[ 1 ] * m[ 10 ] * m[ 7 ] + m[ 5 ] * m[ 2 ] * m[ 11 ] - m[ 1 ] * m[ 6 ] * m[ 11 ] ) / x;
        i[ 7 ] = ( -m[ 8 ] * m[ 6 ] * m[ 3 ] + m[ 4 ] * m[ 10 ] * m[ 3 ] + m[ 8 ] * m[ 2 ] * m[ 7 ]
                   - m[ 0 ] * m[ 10 ] * m[ 7 ] - m[ 4 ] * m[ 2 ] * m[ 11 ] + m[ 0 ] * m[ 6 ] * m[ 11 ] ) / x;
        i[ 11 ] = ( m[ 8 ] * m[ 5 ] * m[ 3 ] - m[ 4 ] * m[ 9 ] * m[ 3 ] - m[ 8 ] * m[ 1 ] * m[ 7 ]
                    + m[ 0 ] * m[ 9 ] * m[ 7 ] + m[ 4 ] * m[ 1 ] * m[ 11 ] - m[ 0 ] * m[ 5 ] * m[ 11 ] ) / x;
        i[ 15 ] = ( -m[ 8 ] * m[ 5 ] * m[ 2 ] + m[ 4 ] * m[ 9 ] * m[ 2 ] + m[ 8 ] * m[ 1 ] * m[ 6 ]
                    - m[ 0 ] * m[ 9 ] * m[ 6 ] - m[ 4 ] * m[ 1 ] * m[ 10 ] + m[ 0 ] * m[ 5 ] * m[ 10 ] ) / x;

        return true;
    }


    public static float Determinant4f( float m[] )
    {
        return
                m[ 12 ] * m[ 9 ] * m[ 6 ] * m[ 3 ] -
                m[ 8 ] * m[ 13 ] * m[ 6 ] * m[ 3 ] -
                m[ 12 ] * m[ 5 ] * m[ 10 ] * m[ 3 ] +
                m[ 4 ] * m[ 13 ] * m[ 10 ] * m[ 3 ] +
                m[ 8 ] * m[ 5 ] * m[ 14 ] * m[ 3 ] -
                m[ 4 ] * m[ 9 ] * m[ 14 ] * m[ 3 ] -
                m[ 12 ] * m[ 9 ] * m[ 2 ] * m[ 7 ] +
                m[ 8 ] * m[ 13 ] * m[ 2 ] * m[ 7 ] +
                m[ 12 ] * m[ 1 ] * m[ 10 ] * m[ 7 ] -
                m[ 0 ] * m[ 13 ] * m[ 10 ] * m[ 7 ] -
                m[ 8 ] * m[ 1 ] * m[ 14 ] * m[ 7 ] +
                m[ 0 ] * m[ 9 ] * m[ 14 ] * m[ 7 ] +
                m[ 12 ] * m[ 5 ] * m[ 2 ] * m[ 11 ] -
                m[ 4 ] * m[ 13 ] * m[ 2 ] * m[ 11 ] -
                m[ 12 ] * m[ 1 ] * m[ 6 ] * m[ 11 ] +
                m[ 0 ] * m[ 13 ] * m[ 6 ] * m[ 11 ] +
                m[ 4 ] * m[ 1 ] * m[ 14 ] * m[ 11 ] -
                m[ 0 ] * m[ 5 ] * m[ 14 ] * m[ 11 ] -
                m[ 8 ] * m[ 5 ] * m[ 2 ] * m[ 15 ] +
                m[ 4 ] * m[ 9 ] * m[ 2 ] * m[ 15 ] +
                m[ 8 ] * m[ 1 ] * m[ 6 ] * m[ 15 ] -
                m[ 0 ] * m[ 9 ] * m[ 6 ] * m[ 15 ] -
                m[ 4 ] * m[ 1 ] * m[ 10 ] * m[ 15 ] +
                m[ 0 ] * m[ 5 ] * m[ 10 ] * m[ 15 ];
    }

    public static boolean GenerateInverseMatrix4f( float i[], float m[] )
    {
        float x = Determinant4f( m );
        if( x == 0 )
        {
            return false;
        }

        i[ 0 ] = ( -m[ 13 ] * m[ 10 ] * m[ 7 ] + m[ 9 ] * m[ 14 ] * m[ 7 ] + m[ 13 ] * m[ 6 ] * m[ 11 ]
                   - m[ 5 ] * m[ 14 ] * m[ 11 ] - m[ 9 ] * m[ 6 ] * m[ 15 ] + m[ 5 ] * m[ 10 ] * m[ 15 ] ) / x;
        i[ 4 ] = ( m[ 12 ] * m[ 10 ] * m[ 7 ] - m[ 8 ] * m[ 14 ] * m[ 7 ] - m[ 12 ] * m[ 6 ] * m[ 11 ]
                   + m[ 4 ] * m[ 14 ] * m[ 11 ] + m[ 8 ] * m[ 6 ] * m[ 15 ] - m[ 4 ] * m[ 10 ] * m[ 15 ] ) / x;
        i[ 8 ] = ( -m[ 12 ] * m[ 9 ] * m[ 7 ] + m[ 8 ] * m[ 13 ] * m[ 7 ] + m[ 12 ] * m[ 5 ] * m[ 11 ]
                   - m[ 4 ] * m[ 13 ] * m[ 11 ] - m[ 8 ] * m[ 5 ] * m[ 15 ] + m[ 4 ] * m[ 9 ] * m[ 15 ] ) / x;
        i[ 12 ] = ( m[ 12 ] * m[ 9 ] * m[ 6 ] - m[ 8 ] * m[ 13 ] * m[ 6 ] - m[ 12 ] * m[ 5 ] * m[ 10 ]
                    + m[ 4 ] * m[ 13 ] * m[ 10 ] + m[ 8 ] * m[ 5 ] * m[ 14 ] - m[ 4 ] * m[ 9 ] * m[ 14 ] ) / x;
        i[ 1 ] = ( m[ 13 ] * m[ 10 ] * m[ 3 ] - m[ 9 ] * m[ 14 ] * m[ 3 ] - m[ 13 ] * m[ 2 ] * m[ 11 ]
                   + m[ 1 ] * m[ 14 ] * m[ 11 ] + m[ 9 ] * m[ 2 ] * m[ 15 ] - m[ 1 ] * m[ 10 ] * m[ 15 ] ) / x;
        i[ 5 ] = ( -m[ 12 ] * m[ 10 ] * m[ 3 ] + m[ 8 ] * m[ 14 ] * m[ 3 ] + m[ 12 ] * m[ 2 ] * m[ 11 ]
                   - m[ 0 ] * m[ 14 ] * m[ 11 ] - m[ 8 ] * m[ 2 ] * m[ 15 ] + m[ 0 ] * m[ 10 ] * m[ 15 ] ) / x;
        i[ 9 ] = ( m[ 12 ] * m[ 9 ] * m[ 3 ] - m[ 8 ] * m[ 13 ] * m[ 3 ] - m[ 12 ] * m[ 1 ] * m[ 11 ]
                   + m[ 0 ] * m[ 13 ] * m[ 11 ] + m[ 8 ] * m[ 1 ] * m[ 15 ] - m[ 0 ] * m[ 9 ] * m[ 15 ] ) / x;
        i[ 13 ] = ( -m[ 12 ] * m[ 9 ] * m[ 2 ] + m[ 8 ] * m[ 13 ] * m[ 2 ] + m[ 12 ] * m[ 1 ] * m[ 10 ]
                    - m[ 0 ] * m[ 13 ] * m[ 10 ] - m[ 8 ] * m[ 1 ] * m[ 14 ] + m[ 0 ] * m[ 9 ] * m[ 14 ] ) / x;
        i[ 2 ] = ( -m[ 13 ] * m[ 6 ] * m[ 3 ] + m[ 5 ] * m[ 14 ] * m[ 3 ] + m[ 13 ] * m[ 2 ] * m[ 7 ]
                   - m[ 1 ] * m[ 14 ] * m[ 7 ] - m[ 5 ] * m[ 2 ] * m[ 15 ] + m[ 1 ] * m[ 6 ] * m[ 15 ] ) / x;
        i[ 6 ] = ( m[ 12 ] * m[ 6 ] * m[ 3 ] - m[ 4 ] * m[ 14 ] * m[ 3 ] - m[ 12 ] * m[ 2 ] * m[ 7 ]
                   + m[ 0 ] * m[ 14 ] * m[ 7 ] + m[ 4 ] * m[ 2 ] * m[ 15 ] - m[ 0 ] * m[ 6 ] * m[ 15 ] ) / x;
        i[ 10 ] = ( -m[ 12 ] * m[ 5 ] * m[ 3 ] + m[ 4 ] * m[ 13 ] * m[ 3 ] + m[ 12 ] * m[ 1 ] * m[ 7 ]
                    - m[ 0 ] * m[ 13 ] * m[ 7 ] - m[ 4 ] * m[ 1 ] * m[ 15 ] + m[ 0 ] * m[ 5 ] * m[ 15 ] ) / x;
        i[ 14 ] = ( m[ 12 ] * m[ 5 ] * m[ 2 ] - m[ 4 ] * m[ 13 ] * m[ 2 ] - m[ 12 ] * m[ 1 ] * m[ 6 ]
                    + m[ 0 ] * m[ 13 ] * m[ 6 ] + m[ 4 ] * m[ 1 ] * m[ 14 ] - m[ 0 ] * m[ 5 ] * m[ 14 ] ) / x;
        i[ 3 ] = ( m[ 9 ] * m[ 6 ] * m[ 3 ] - m[ 5 ] * m[ 10 ] * m[ 3 ] - m[ 9 ] * m[ 2 ] * m[ 7 ]
                   + m[ 1 ] * m[ 10 ] * m[ 7 ] + m[ 5 ] * m[ 2 ] * m[ 11 ] - m[ 1 ] * m[ 6 ] * m[ 11 ] ) / x;
        i[ 7 ] = ( -m[ 8 ] * m[ 6 ] * m[ 3 ] + m[ 4 ] * m[ 10 ] * m[ 3 ] + m[ 8 ] * m[ 2 ] * m[ 7 ]
                   - m[ 0 ] * m[ 10 ] * m[ 7 ] - m[ 4 ] * m[ 2 ] * m[ 11 ] + m[ 0 ] * m[ 6 ] * m[ 11 ] ) / x;
        i[ 11 ] = ( m[ 8 ] * m[ 5 ] * m[ 3 ] - m[ 4 ] * m[ 9 ] * m[ 3 ] - m[ 8 ] * m[ 1 ] * m[ 7 ]
                    + m[ 0 ] * m[ 9 ] * m[ 7 ] + m[ 4 ] * m[ 1 ] * m[ 11 ] - m[ 0 ] * m[ 5 ] * m[ 11 ] ) / x;
        i[ 15 ] = ( -m[ 8 ] * m[ 5 ] * m[ 2 ] + m[ 4 ] * m[ 9 ] * m[ 2 ] + m[ 8 ] * m[ 1 ] * m[ 6 ]
                    - m[ 0 ] * m[ 9 ] * m[ 6 ] - m[ 4 ] * m[ 1 ] * m[ 10 ] + m[ 0 ] * m[ 5 ] * m[ 10 ] ) / x;

        return true;
    }


}
