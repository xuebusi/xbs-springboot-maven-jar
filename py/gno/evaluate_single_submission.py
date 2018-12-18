import json
from os import listdir, path

import numpy as np

from py.gno.evaluation_metrics import evaluation_metrics_for_segmentation, evaluation_metrics_for_classification, \
    evaluation_metrics_for_fovea_location


def evaluate_single_submission(results_folder, gt_folder, output_path=None, export_table=False, is_training=False,
                               team_name=None):
    inside_results_folder = listdir(results_folder)
    if '__MACOSX' in inside_results_folder:
        inside_results_folder.remove('__MACOSX')
    if ((not (path.exists(path.join(results_folder, 'segmentation'))) and path.exists(
            path.join(results_folder, inside_results_folder[0], 'segmentation'))) or
            (not (path.exists(path.join(results_folder, 'classification_results.csv'))) and path.exists(
                path.join(results_folder, inside_results_folder[0], 'classification_results.csv'))) or
            (not (path.exists(path.join(results_folder, 'fovea_localization_results.csv'))) and path.exists(
                path.join(results_folder, inside_results_folder[0], 'fovea_localization_results.csv'))) or
            (not (path.exists(path.join(results_folder, 'fovea_location_results.csv'))) and path.exists(
                path.join(results_folder, inside_results_folder[0], 'fovea_location_results.csv')))):
        results_folder = path.join(results_folder, inside_results_folder[0])

    segmentation_folder = path.join(results_folder, 'segmentation')

    if path.exists(segmentation_folder):
        gt_segmentation_folder = path.join(gt_folder, 'Disc_Cup_Masks')

        try:
            mean_cup_dice, mean_disc_dice, mae_cdr = evaluation_metrics_for_segmentation.evaluate_segmentation_results(
                segmentation_folder, gt_segmentation_folder,
                output_path=output_path,
                export_table=export_table,
                is_training=is_training)
            segmentation_performance = [mean_cup_dice, mean_disc_dice, mae_cdr]
        except:
            segmentation_performance = [np.nan, np.nan, np.nan]
    else:
        segmentation_performance = [np.nan, np.nan, np.nan]

    classification_filename = path.join(results_folder, 'classification_results.csv')
    if path.exists(classification_filename):
        if is_training:
            gt_classification_folder = path.join(gt_folder, 'Disc_Cup_Masks')
        else:
            gt_classification_folder = gt_folder
        try:
            auc, reference_sensitivity = evaluation_metrics_for_classification.evaluate_classification_results(
                classification_filename, gt_classification_folder,
                output_path=output_path,
                is_training=is_training)
            classification_performance = [auc, reference_sensitivity]
        except:
            classification_performance = [np.nan, np.nan]
    else:
        classification_performance = [np.nan, np.nan]
    fovea_location_filename = path.join(results_folder, 'fovea_location_results.csv')
    if not path.exists(fovea_location_filename):
        fovea_location_filename = path.join(results_folder, 'fovea_localization_results.csv')

    if path.exists(fovea_location_filename):
        try:
            if is_training:
                gt_filename = path.join(gt_folder, 'Fovea_location.xlsx')
            else:
                gt_filename = path.join(gt_folder, 'Fovea_locations.xlsx')
            fovea_location_performance = evaluation_metrics_for_fovea_location.evaluate_fovea_location_results(
                fovea_location_filename, gt_filename,
                output_path=output_path,
                is_training=is_training)
        except:
            fovea_location_performance = np.nan
    else:
        fovea_location_performance = np.nan

    return segmentation_performance, classification_performance, fovea_location_performance


if __name__ == '__main__':
    results_folder = '/Users/v_shiyanjun/github/xbs-springboot-maven-jar/py/gno/data/rf/'
    gt_folder = '/Users/v_shiyanjun/github/xbs-springboot-maven-jar/py/gno/data/gt/'

    evaluate_result = evaluate_single_submission(results_folder, gt_folder)

    data = [{
        'name': 'meanCupDice',
        'value': str(evaluate_result[0][0]),
        'type': ''
    }, {
        'name': 'meanDiscDice',
        'value': str(evaluate_result[0][1]),
        'type': ''
    }, {
        'name': 'maeCdr',
        'value': str(evaluate_result[0][2]),
        'type': ''
    }, {
        'name': 'auc',
        'value': str(evaluate_result[1][0]),
        'type': ''
    }, {
        'name': 'referenceSensitivity',
        'value': str(evaluate_result[1][1]),
        'type': ''
    }]

    result = {
        'data': data,
        'errorCode': 0,
        'errorMsg': 'success'
    }

    json_result = json.dumps(result, ensure_ascii=False)

    print(json_result)
