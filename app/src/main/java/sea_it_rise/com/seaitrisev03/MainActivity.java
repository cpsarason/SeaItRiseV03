package sea_it_rise.com.seaitrisev03;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;


import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.style.layers.RasterLayer;
import com.mapbox.mapboxsdk.style.sources.RasterSource;
import com.mapbox.mapboxsdk.style.sources.TileSet;
import com.mapbox.mapboxsdk.style.layers.Layer;
import com.mapbox.mapboxsdk.style.sources.VectorSource;
import com.mapbox.mapboxsdk.constants.Style;

/*import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerMode;
import com.mapbox.mapboxsdk.plugins.locationlayer.LocationLayerPlugin;
import com.mapbox.services.android.location.LostLocationEngine;
import com.mapbox.services.android.telemetry.location.LocationEngine;
import com.mapbox.services.android.telemetry.location.LocationEngineListener;
import com.mapbox.services.android.telemetry.location.LocationEnginePriority;
import com.mapbox.services.android.telemetry.permissions.PermissionsListener;
import com.mapbox.services.android.telemetry.permissions.PermissionsManager;*/


import java.sql.Array;
import java.util.List;

import static com.mapbox.mapboxsdk.style.layers.Property.NONE;
import static com.mapbox.mapboxsdk.style.layers.Property.VISIBLE;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.circleColor;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.circleRadius;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.rasterOpacity;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.visibility;


public class MainActivity extends AppCompatActivity {
    private MapView mapView;
    private MapboxMap map;
    private List layerList;
    private String layerString;

    private RasterLayer noaaConfLayer3;
    private RasterLayer noaaSLRLayer3;
    private RasterLayer noaaConfLayer6;
    private RasterLayer noaaSLRLayer6;
    private int menu_noaa_conf_3ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoiY3BzYXJhc29uIiwiYSI6ImNqNnpoZXdmMDBpcDMyd3NnaXNmMGFlcmIifQ.DYNQK4o4FWPBVcMG-rPU7g");

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mapView = (MapView) findViewById(R.id.mapView);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                //map = mapboxMap;
                RasterSource noaaConfSource3 = new RasterSource(
                        "noaa-3ft-conf-source",
                        new TileSet("tileset", "https://www.coast.noaa.gov/arcgis/rest/services/dc_slr/conf_3ft/MapServer/tile/{z}/{y}/{x}"), 256);
                RasterSource noaaConfSource6 = new RasterSource(
                        "noaa-6ft-conf-source",
                        new TileSet("tileset", "https://www.coast.noaa.gov/arcgis/rest/services/dc_slr/conf_6ft/MapServer/tile/{z}/{y}/{x}"), 256);
                RasterSource noaaSLRSource3 = new RasterSource(
                        "noaa-3ft-slr-source",
                        new TileSet("tileset", "https://www.coast.noaa.gov/arcgis/rest/services/dc_slr/slr_3ft/MapServer/tile/{z}/{y}/{x}"), 256);
                RasterSource noaaSLRSource6 = new RasterSource(
                        "noaa-6ft-slr-source",
                        new TileSet("tileset", "https://www.coast.noaa.gov/arcgis/rest/services/dc_slr/slr_6ft/MapServer/tile/{z}/{y}/{x}"), 256);

                mapboxMap.addSource(noaaConfSource3);
                mapboxMap.addSource(noaaSLRSource3);
                mapboxMap.addSource(noaaConfSource6);
                mapboxMap.addSource(noaaSLRSource6);

                // Add the 3ft conf map source to the map.
                noaaConfLayer3 = new RasterLayer("noaa-layer-3-conf","noaa-3ft-conf-source");
                noaaConfLayer3.setProperties( rasterOpacity((float)0.5),
                        visibility(NONE) );

                // Add the 6ft conf web map source to the map.
                noaaConfLayer6 = new RasterLayer("noaa-layer-6-conf","noaa-6ft-conf-source");
                noaaConfLayer6.setProperties( rasterOpacity((float)0.5),
                        visibility(NONE));
                // Add the 3ft slr map source to the map.
                noaaSLRLayer3 = new RasterLayer("noaa-layer-3-slr","noaa-3ft-slr-source");
                noaaSLRLayer3.setProperties( rasterOpacity((float)0.5),
                        visibility(NONE));

                // Add the 6ft web map source to the map.
                noaaSLRLayer6 = new RasterLayer("noaa-layer-6-slr","noaa-6ft-slr-source");
                noaaSLRLayer6.setProperties( rasterOpacity((float)0.5),
                        visibility(NONE));

                mapboxMap.addLayerBelow(noaaConfLayer3,"aeroway-taxiway");
                mapboxMap.addLayerBelow(noaaConfLayer6, "aeroway-taxiway");
                mapboxMap.addLayerBelow(noaaSLRLayer3,"aeroway-taxiway");
                mapboxMap.addLayerBelow(noaaSLRLayer6, "aeroway-taxiway");

                //mapboxMap.getMy

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "GeoLocation coming here soon", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //mapView = (MapView) findViewById(R.id.mapViewSeattle);
                //toggleLayer();
            }
        });
       /* FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //mapView = (MapView) findViewById(R.id.mapViewSeattle);
                //toggleLayer();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        switch (item.getItemId()) {
            case R.id.menu_noaa_conf_3ft:
                //map.removeLayer(webMapLayer);
                //map.setStyleUrl(Style.MAPBOX_STREETS);

                if (VISIBLE.equals(noaaConfLayer3.getVisibility().getValue())) {
                    noaaConfLayer3.setProperties(visibility(NONE));
                    item.setChecked(false);
                } else {
                    noaaConfLayer3.setProperties(visibility(VISIBLE));
                    item.setChecked(true);
                }
                return true;
            case R.id.menu_noaa_slr_3ft:
                if (VISIBLE.equals(noaaSLRLayer3.getVisibility().getValue())) {
                    noaaSLRLayer3.setProperties(visibility(NONE));
                    item.setChecked(false);
                } else {
                    noaaSLRLayer3.setProperties(visibility(VISIBLE));
                    item.setChecked(true);
                }
                return true;
            case R.id.menu_noaa_conf_6ft:
                if (VISIBLE.equals(noaaConfLayer6.getVisibility().getValue())) {
                    noaaConfLayer6.setProperties(visibility(NONE));
                    item.setChecked(false);
                } else {
                    noaaConfLayer6.setProperties(visibility(VISIBLE));
                    item.setChecked(true);
                }
                return true;
            case R.id.menu_noaa_slr_6ft:
                if (VISIBLE.equals(noaaSLRLayer6.getVisibility().getValue())) {
                    noaaSLRLayer6.setProperties(visibility(NONE));
                    item.setChecked(false);
                } else {
                    noaaSLRLayer6.setProperties(visibility(VISIBLE));
                    item.setChecked(true);
                }
                return true;
            case R.id.menu_satellite:
                //map.setStyleUrl(Style.SATELLITE);
                return true;
            case R.id.menu_satellite_streets:
                //map.setStyleUrl(Style.SATELLITE_STREETS);
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    private void toggleLayer() {
        //return true;
        /*Layer layer = mapboxMap.getLayer("noaa-layer-3");
        if (layer != null) {
            if (VISIBLE.equals(layer.getVisibility().getValue())) {
                layer.setProperties(visibility(NONE));
            } else {
                layer.setProperties(visibility(VISIBLE));
            }
        }*/
    }

}
