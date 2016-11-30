package android.univ.lille1.fr.forplants.plants;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.univ.lille1.fr.forplants.R;
import android.univ.lille1.fr.forplants.addeditplant.AddEditPlantActivity;
import android.univ.lille1.fr.forplants.data.Plant;
import android.univ.lille1.fr.forplants.detailsplant.DetailsPlantActivity;
import android.univ.lille1.fr.forplants.util.ActivityUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class PlantsFragment extends Fragment implements PlantsContract.View {

    private TextView dateJour;
    private PlantsContract.Presenter mPresenter;
    private View noPlantsView;
    private ImageView noPlantsIcon;
    private TextView noPlantMainText;
    private TextView noPlantAddPlant;
    private LinearLayout mPlantsView;
    private RecyclerView mRecyclerView;
    private PlantsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public PlantsFragment() {
        // Requires empty public constructor
    }

    public static PlantsFragment newInstance() {
        return new PlantsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new PlantsAdapter(new ArrayList<Plant>(0));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
        mPresenter.loadPlants();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_plants, container, false);

        // Set up Plants View
        dateJour = (TextView) root.findViewById(R.id.date_jour);
        dateJour.setText(dateJour.getText() + " " + ActivityUtils.getDATE_SYSTEM());
        mRecyclerView = (RecyclerView) root.findViewById(R.id.listplants);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mPlantsView = (LinearLayout) root.findViewById(R.id.plantsRV);


        // Set up No Plants View
        noPlantsView= root.findViewById(R.id.noTasks);
        noPlantsIcon = (ImageView) root.findViewById(R.id.noPlantsIcon);
        noPlantMainText = (TextView) root.findViewById(R.id.noPlantsMain);
        noPlantAddPlant = (TextView) root.findViewById(R.id.noPlantsAdd);
        noPlantAddPlant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addPlants();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addPlant();
            }
        });

        mPresenter.loadPlants();


        return root;
    }


    @Override
    public void showPlants(List<Plant> plants) {

        mAdapter.replaceData(plants);

        dateJour.setText("Date du jour : " + ActivityUtils.getDATE_SYSTEM());
        noPlantsView.setVisibility(View.GONE);
        mPlantsView.setVisibility(View.VISIBLE);

    }

    @Override
    public void showNoPlants() {
        mPlantsView.setVisibility(View.GONE);
        noPlantsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAddPlant() {
        Intent intent = new Intent(getContext(), AddEditPlantActivity.class);
        intent.putExtra("plant", -1);
        startActivityForResult(intent, AddEditPlantActivity.REQUEST_ADD_PLANT);
    }

    @Override
    public void showSuccessfullySavedMessage() {
        showMessage(getString(R.string.successfully_saved_plant_message));
    }

    @Override
    public void showPlant(int id_plant) {
        Intent intent = new Intent(getContext(), DetailsPlantActivity.class);
        intent.putExtra("plant", id_plant);
        startActivityForResult(intent, DetailsPlantActivity.DETAIL_PLANT);
    }

    @Override
    public void showSuccessfullyDeletedMessage() {
        showMessage(getString(R.string.successfully_deleted_plant_message));
    }

    @Override
    public void showMessageNotHandled() {
        Log.d("MESSAGE", "Ce type de message n'est pas géré par l'application");

    }

    @Override
    public void showSuccessfullyArrosedMessage() {
        showMessage(getString(R.string.successfully_arrosed_plant_message));
    }

    @Override
    public void showAlreadyArrosed() {
        showMessage(getString(R.string.failed_arrosed));
    }

    private void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(PlantsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    /**
     * Adapter pour le recyclerview
     */
    private class PlantsAdapter extends RecyclerView.Adapter<PlantsAdapter.ViewHolder> {

        private List<Plant> mDataset;

        /**
         * Contient notre row avec les traitements demandées click & longclick
         */
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

            // each data item is just a string in this case

            public int id_plant;
            public TextView nom;
            public TextView date;
            public TextView dateArrosage;
            public ImageView img;
            public View mView;

            public ViewHolder(View v) {
                super(v);
                nom = (TextView) v.findViewById(R.id.nom_plant);
                date = (TextView) v.findViewById(R.id.date_arroser);
                dateArrosage = (TextView) v.findViewById(R.id.date_arrosage);
                img = (ImageView) v.findViewById(R.id.arroser);
                mView = v;
                v.setOnClickListener(this);
                v.setOnLongClickListener(this);
            }

            @Override
            public void onClick(View view) {
                mPresenter.showPlantsDetails(id_plant);
            }

            @Override
            public boolean onLongClick(View view) {

                ColorDrawable imgColor = (ColorDrawable) img.getBackground();
                int colorId = imgColor.getColor();
                // Couleur pour le vert
                if (colorId != -16738048) {
                    mPresenter.arroserPlant(id_plant);
                    return true;
                }
                mPresenter.cannotArroser();
                return true;
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public PlantsAdapter(ArrayList<Plant> myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_plant, parent, false);
            // set the view's size, margins, paddings and layout parameters
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(PlantsAdapter.ViewHolder holder, int position) {

            final Plant plant = mDataset.get(position);

            long nbJour = ActivityUtils.dayBetweenTwoDate(plant.getDateArrosage());
            if (nbJour > 1){
            // View verte
                holder.img.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
            }else if(nbJour == 1 ){
                // View orange
                holder.img.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.orange));
            }else{
            // View Rouge
                holder.img.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.red));

            }
            holder.nom.setText(plant.getNom());
            holder.date.setText(plant.getDate());
            holder.dateArrosage.setText(plant.getDateArrosage());
            holder.id_plant = plant.getId();

        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }

        public void replaceData(List<Plant> plants) {
            setList(plants);
            notifyDataSetChanged();
        }

        private void setList(List<Plant> plant) {
            mDataset = plant;
        }
    }




}

