from os import path

from scipy.interpolate import interp1d
from sklearn.metrics import roc_curve, roc_auc_score

from py.gno.util.file_management import read_csv_classification_results, get_labels_from_training_data, read_gt_labels, \
    sort_scores_by_filename


def get_roc_curve(predicted_scores, gt_labels):
    fpr, tpr, _ = roc_curve(gt_labels, predicted_scores)

    auc = roc_auc_score(gt_labels, predicted_scores)

    return tpr, fpr, auc


def get_sensitivity_at_given_specificity(sensitivity, specificity, specificity_reference=0.85):
    sensitivity_interp = interp1d(specificity, sensitivity)
    sensitivity_value = sensitivity_interp(specificity_reference)

    return sensitivity_value


def evaluate_classification_results(prediction_filename, gt_folder, output_path=None, is_training=False):
    image_filenames, predicted_scores = read_csv_classification_results(prediction_filename)

    if is_training:
        gt_filenames, gt_labels = get_labels_from_training_data(gt_folder)
    else:
        gt_filenames, gt_labels = read_gt_labels(path.join(gt_folder, 'GT.xlsx'))

    gt_labels = sort_scores_by_filename(image_filenames, gt_filenames, gt_labels)

    sensitivity, fpr, auc = get_roc_curve(predicted_scores, gt_labels)
    specificity = 1 - fpr
    print('AUC = {}'.format(str(auc)))

    sensitivity_at_reference_value = get_sensitivity_at_given_specificity(sensitivity, specificity)
    print('Reference Sensitivity = {}'.format(str(sensitivity_at_reference_value)))

    return auc, sensitivity_at_reference_value
