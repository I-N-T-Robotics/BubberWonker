package frc.introbotics.intlib.vision;

public enum ReadValue {
  /** Whether the limelight has any valid targets (0 or 1) */
  tv,

  /**
   * Horizontal Offset From Crosshair To Target (LL1: -27 degrees to 27 degrees | LL2: -29.8 to 29.8
   * degrees)
   */
  tx,

  /**
   * Vertical Offset From Crosshair To Target (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to
   * 24.85 degrees)
   */
  ty,

  /** Target Area (0% of image to 100% of image) */
  ta,

  /** Skew or rotation (-90 degrees to 0 degrees) */
  ts,

  /** The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency. */
  tl,

  /** Sidelength of shortest side of the fitted bounding box (pixels) */
  tshort,

  /** Sidelength of longest side of the fitted bounding box (pixels) */
  tlong,

  /** Horizontal sidelength of the rough bounding box (0 - 320 pixels) */
  thor,

  /** Vertical sidelength of the rough bounding box (0 - 320 pixels) */
  tvert,

  /** True active pipeline index of the camera (0 .. 9) */
  getpipe,

  /** Results of a 3D position solution, 6 numbers: Translation (x,y,y) Rotation(pitch,yaw,roll) */
  camtran,

  /////////////////
  // Raw Targets //
  /////////////////

  /** Raw Screenspace X */
  tx0,
  tx1,
  tx2,

  /** Raw Screenspace Y */
  ty0,
  ty1,
  ty2,

  /** Area (0% of image to 100% of image) */
  ta0,
  ta1,
  ta2,

  /** Skew or rotation (-90 degrees to 0 degrees) */
  ts0,
  ts1,
  ts2,

  ////////////////////
  // Raw Crosshairs //
  ////////////////////

  /** Crosshair A X in normalized screen space */
  cx0,

  /** Crosshair A Y in normalized screen space */
  cy0,

  /** Crosshair B X in normalized screen space */
  cx1,

  /** Crosshair B Y in normalized screen space */
  cy1,

  /** */
  tcornx,

  /** */
  tcorny
}
